package com.hvalinski.storage.controllers;

import com.hvalinski.storage.dataBase.*;
import com.hvalinski.storage.dataBase.printInfo.PrintProduct;
import com.hvalinski.storage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

//import org.springframework.web.bind.annotation.RequestParam;
//@RequestParam(name="name", required=false, defaultValue="World") String name,
@Controller
public class MainController {

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
    @GetMapping("/category/{id}")
    public String home(@PathVariable(value = "id") Long id, Model model){
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        ArrayList<PrintProduct> printProductArrayList = new ArrayList<>();
        Optional<Categories> categories = categoriesRepository.findById(id);
        Iterable<Products> products = productRepository.findAll();
        Iterator<Products> iterator = products.iterator();
        while (iterator.hasNext()) {
            Products element = iterator.next();
            PrintProduct e = new PrintProduct();
            if(element.getCategory_id() == categories.get().getCategory_id()) {
                e.setProduct_id(element.getProduct_id());
                e.setProduct_name(element.getProduct_name());
                e.setDescription(element.getDescription());
                e.setImage_path(element.getImage_path());
                e.setProduct_count(element.getProduct_count());
                e.setProduct_price(element.getProduct_price());
                printProductArrayList.add(e);
            }
        }
        model.addAttribute("products",printProductArrayList);
        return "home";
        }
        else {return "login";}
        }



    @GetMapping("/")
    public String main(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
           Iterable<Categories> categories = categoriesRepository.findAll();
               model.addAttribute("categories", categories);
            return "main";
        }
        else {return "login";}
    }

    @GetMapping("/admin")
    public String home_admin(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
            ArrayList<PrintProduct> printProductArrayList = new ArrayList<>();
            Iterable<Products> products = productRepository.findAll();
            Iterator<Products> iterator = products.iterator();
            while (iterator.hasNext()) {
                Products element = iterator.next();
                PrintProduct e = new PrintProduct();
                e.setProduct_id(element.getProduct_id());
                e.setProduct_name(element.getProduct_name());
                e.setDescription(element.getDescription());
                e.setImage_path(element.getImage_path());
                e.setProduct_count(element.getProduct_count());
                e.setProduct_price(element.getProduct_price());
                Categories categories = categoriesRepository.findById(element.getCategory_id()).orElseThrow();
                e.setCategory_id(categories.getCategory_id());
                e.setCategory_name(categories.getCategory_name());
                printProductArrayList.add(e);
            }
            model.addAttribute("products",printProductArrayList);
            return "home-admin";
        }
        else {return "login";}
    }

    @GetMapping("/about")
    public String about(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "about";
    }
        else {return "login";}
    }

}


