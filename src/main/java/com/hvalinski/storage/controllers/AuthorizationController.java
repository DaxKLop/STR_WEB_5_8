package com.hvalinski.storage.controllers;

import com.hvalinski.storage.dataBase.Person;
import com.hvalinski.storage.dataBase.Roles;
import com.hvalinski.storage.dataBase.Users;
import com.hvalinski.storage.dataBase.printInfo.AuthorizedUser;
import com.hvalinski.storage.repository.PersonRepository;
import com.hvalinski.storage.repository.RolesRepository;
import com.hvalinski.storage.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {


    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RolesRepository rolesRepository;
    public static AuthorizedUser authorizedUser = new AuthorizedUser();

    private String errorMessage = "";
    private String errorMessage_reg = "";
    private String regMessage = "";

    private String login = "";
    private String fullName = "";
    private String phone = "";
    private String email = "";

    @GetMapping("/login")
    public String home(Model model) {
        authorizedUser.setAuthorized(false);
        authorizedUser.setRole("Пользователь");

        errorMessage_reg = "";
        this.login = "";
        this.fullName = "";
        this.phone = "";
        this.email = "";

        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("regMessage", regMessage);

        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password, Model model) {

        try
        {
            Users users = usersRepository.findByLogin(login).orElseThrow();
            if(users.getPassword().equals(password))
            {
                errorMessage = "";
                regMessage = "";

                Roles role = rolesRepository.findById(users.getUser_id()).orElseThrow();
                authorizedUser.setAuthorized(true);
                authorizedUser.setUser_id(users.getUser_id());
                if(role.getRole_name().equals("Администратор"))
                {
                    authorizedUser.setRole("Администратор");
                    return "redirect:/admin";
                }
                authorizedUser.setRole("Пользователь");
                return "redirect:/";
            }
            else {
                errorMessage = "Incorrect password";
            }
        }
        catch (Exception e)
        {
            errorMessage = "Incorrect login";
        }


        return "redirect:/login";

    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("login", login);
        model.addAttribute("fullName", fullName);
        model.addAttribute("phone", phone);
        model.addAttribute("email", email);

        errorMessage = "";

        model.addAttribute("errorMessage", errorMessage_reg);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String login, @RequestParam String password, @RequestParam String fullName, @RequestParam String phone, @RequestParam String email, Model model) {

        try {
            Users users = usersRepository.findByLogin(login).orElseThrow();
            errorMessage_reg = "Login is already used";
            this.login = login;
            this.fullName = fullName;
            this.phone = phone;
            this.email = email;
        } catch (Exception e) {
            Users users = new Users(login, password);
            usersRepository.save(users);
            Person person = new Person(users.getUser_id(), fullName,phone, email);
            personRepository.save(person);
            Roles roles = new Roles(users.getUser_id(), "Пользователь");
            rolesRepository.save(roles);
            regMessage = "You have been registred";
            errorMessage_reg = "";
            errorMessage = "";
            return "redirect:/login";
        }
    return "redirect:/registration";
    }
}