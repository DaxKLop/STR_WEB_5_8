package com.hvalinski.storage.controllers;

import com.hvalinski.storage.dataBase.*;
import com.hvalinski.storage.dataBase.printInfo.PrintProduct;
import com.hvalinski.storage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
@Controller
public class ProductController {

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

    @GetMapping("/storage/add_item")
    public String storageAddItem(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        Iterable<Categories> categories = categoriesRepository.findAll();
        model.addAttribute("categories", categories);
        Iterable<Products> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "storage-add-item";
        }
        else {return "login";}
    }

    @PostMapping("/storage/add_item")
    public String storageItemAdd(@RequestParam String product_name, @RequestParam String description, @RequestParam Long category_id,@RequestParam Long product_price, @RequestParam Long product_count, @RequestParam("imageFile") MultipartFile image_path, Model model) {
        Products products = new Products(category_id, product_name, description, product_count,product_price);
        productRepository.save(products);
        if (!image_path.isEmpty()) {
            try {
                String fileName = "image" + products.getProduct_id() + ".png";
                String uploadDir = "D:/PSP_kursach/storage/src/main/resources/templates/images/";
                File saveFile = new File(uploadDir, fileName);
                image_path.transferTo(saveFile);
                products.setImage_path(uploadDir+fileName);
                productRepository.save(products);
                System.out.println(uploadDir+fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return "redirect:/storage";
    }

    @GetMapping("/storage/{id}/edit")
    public String storageDetails(@PathVariable(value = "id") Long id, Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        if (!productRepository.existsById(id)) {
            return "redirect:/storage";
        }
        Iterable<Categories> categories = categoriesRepository.findAll();
        model.addAttribute("categories", categories);
        Optional<Products> products = productRepository.findById(id);
        PrintProduct printProduct = new PrintProduct();
        printProduct.setProduct_name(products.get().getProduct_name());
        printProduct.setCategory_id(products.get().getCategory_id());
        printProduct.setDescription(products.get().getDescription());
        printProduct.setProduct_count(products.get().getProduct_count());
        printProduct.setProduct_price(products.get().getProduct_price());
        ArrayList<Products> res = new ArrayList<>();
        model.addAttribute("product", printProduct);
        return "storage-edit-product";
        }
        else {return "login";}
    }
    @PostMapping("/storage/{id}/edit")
    public String storageUsersEdit(@PathVariable(value = "id") Long id, @RequestParam String product_name, @RequestParam Long product_price,@RequestParam Long product_count,@RequestParam String description, @RequestParam Long category_id, @RequestParam("imageFile") MultipartFile image_path, Model model) {

        Products products = productRepository.findById(id).orElseThrow();
        products.setProduct_name(product_name);
        products.setDescription(description);
        products.setCategory_id(category_id);
        products.setProduct_count(product_count);
        products.setProduct_price(product_price);
        productRepository.save(products);
        String filePath = products.getImage_path();
        Path path = Paths.get(filePath);

        if (!image_path.isEmpty()) {
            try {
                if (Files.exists(path)) {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                    }
                }
                String fileName = "image" + products.getProduct_id() + ".png";
                String uploadDir = "D:/PSP_kursach/storage/src/main/resources/templates/images/";
                File saveFile = new File(uploadDir, fileName);
                image_path.transferTo(saveFile);
                products.setImage_path(uploadDir+fileName);
                productRepository.save(products);
                System.out.println(uploadDir+fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "redirect:/storage";
    }

    @PostMapping("/storage/{id}/remove")
    public String storageUsersRemove(@PathVariable(value = "id") Long id, Model model) {
        Products products = productRepository.findById(id).orElseThrow();
        productRepository.delete(products);
        String filePath = products.getImage_path();
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
            }
        }
        return "redirect:/storage";
    }
}
