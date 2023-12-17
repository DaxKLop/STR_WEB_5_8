package com.hvalinski.storage.controllers;
import com.hvalinski.storage.dataBase.*;
import com.hvalinski.storage.dataBase.printInfo.PrintProduct;
import com.hvalinski.storage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


@Controller
public class StorageController {

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

    @GetMapping("/storage")
    public String storageMain(Model model) {
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


        return "storage-product";
        }
        else {return "login";}
    }



    @GetMapping("/storage/add_category")
    public String storageAddCategory(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        Iterable<Categories> categories = categoriesRepository.findAll();
        model.addAttribute("categories", categories);
        return "storage-add-category";
    }
        else {return "login";}
    }

    @PostMapping("/storage/add_category")
    public String storageCategoryAdd(@RequestParam String title, @RequestParam("imageFile") MultipartFile image_path, Model model) {
        Categories categories = new Categories(title);
        categoriesRepository.save(categories);
        if (!image_path.isEmpty()) {
            try {
                String fileName = "category" + categories.getCategory_id() + ".png";
                String uploadDir = "D:/PSP_kursach/storage/src/main/resources/templates/images/";
                File saveFile = new File(uploadDir, fileName);
                image_path.transferTo(saveFile);
                categories.setImage_path(uploadDir+fileName);
                categoriesRepository.save(categories);
                System.out.println(uploadDir+fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "redirect:/storage";

    }






}
