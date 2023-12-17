package com.hvalinski.storage.controllers;

import com.hvalinski.storage.dataBase.*;
import com.hvalinski.storage.dataBase.printInfo.PrintUsers;
import com.hvalinski.storage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
@Controller
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private DeliveriesRepository deliveriesRepository;
    @Autowired
    private StoresRepository storesRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/users")
    public String usersMain(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        ArrayList<PrintUsers> printUsersArrayList = new ArrayList<>();
        Iterable<Users> users = usersRepository.findAll();
        Iterator<Users> iterator = users.iterator();
        while (iterator.hasNext()) {
            Users element = iterator.next();
            PrintUsers e = new PrintUsers();
            e.setUser_id(element.getUser_id());
            e.setLogin(element.getLogin());
            e.setPassword(element.getPassword());
            Person person = personRepository.findById( element.getUser_id()).orElseThrow();
            e.setPerson_name(person.getPerson_name());
            e.setMail(person.getMail());
            e.setPhone(person.getPhone());
            Roles roles = rolesRepository.findById(element.getUser_id()).orElseThrow();
            e.setRole_name(roles.getRole_name());
            printUsersArrayList.add(e);
        }
        model.addAttribute("users",printUsersArrayList);
        return "storage-users";
        }
        else {return "login";}
    }

    @GetMapping("/storage/add_user")
    public String storageAddUser(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users", users);
        Iterable<Roles> roles = rolesRepository.findAll();
        model.addAttribute("roles", roles);
        Iterable<Person> person = personRepository.findAll();
        model.addAttribute("person", person);
        return "storage-add-user";
        }
        else {return "login";}
    }
    @PostMapping("/storage/add_user")
    public String storageUsersAdd(@RequestParam String login, @RequestParam String password, @RequestParam String role, @RequestParam String phone, @RequestParam String email, @RequestParam String name, Model model) {
        Users users = new Users(login, password);
        usersRepository.save(users);
        Roles roles = new Roles(users.getUser_id(), role);
        rolesRepository.save(roles);
        Person person = new Person(users.getUser_id(), name, phone, email);
        personRepository.save(person);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String storageDetails(@PathVariable(value = "id") Long id, Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        if (!usersRepository.existsById(id)) {
            return "redirect:/users";
        }
        Optional<Users> users = usersRepository.findById(id);
        Optional<Roles> roles = rolesRepository.findById(id);
        Optional<Person> person = personRepository.findById(id);
        PrintUsers printUsers = new PrintUsers();
        printUsers.setLogin(users.get().getLogin());
        printUsers.setPassword(users.get().getPassword());
        printUsers.setRole_name(roles.get().getRole_name());
        printUsers.setPerson_name(person.get().getPerson_name());
        printUsers.setMail(person.get().getMail());
        printUsers.setPhone(person.get().getPhone());
        ArrayList<Users> res = new ArrayList<>();
        model.addAttribute("users", printUsers);
        return "storage-edit-user";
        }
        else {return "login";}
    }


    @PostMapping("/users/{id}/edit")
    public String storageUsersEdit(@PathVariable(value = "id") Long id, @RequestParam String login, @RequestParam String password, @RequestParam String role, @RequestParam String phone, @RequestParam String email, @RequestParam String name, Model model) {
        Users users = usersRepository.findById(id).orElseThrow();
        users.setLogin(login);
        users.setPassword(password);
        usersRepository.save(users);
        Roles roles = rolesRepository.findById(id).orElseThrow();
        roles.setRole_name(role);
        rolesRepository.save(roles);
        Person person = personRepository.findById(id).orElseThrow();
        person.setPerson_name(name);
        person.setMail(email);
        person.setPhone(phone);
        personRepository.save(person);
        return "redirect:/users";
    }

    @PostMapping("/users/{id}/remove")
    public String storageUsersRemove(@PathVariable(value = "id") Long id, Model model) {
        Roles roles = rolesRepository.findById(id).orElseThrow();
        rolesRepository.delete(roles);
        Person person = personRepository.findById(id).orElseThrow();
        personRepository.delete(person);
        Users users = usersRepository.findById(id).orElseThrow();
        usersRepository.delete(users);
        return "redirect:/users";
    }
}
