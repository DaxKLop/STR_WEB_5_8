package com.hvalinski.storage.controllers;
import com.hvalinski.storage.dataBase.Categories;
import com.hvalinski.storage.dataBase.Stores;
import com.hvalinski.storage.dataBase.printInfo.PrintStore;
import com.hvalinski.storage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class StoreController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private StoresRepository storesRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PurchaseRepository purchasesRepository;
    @Autowired
    private PurchaseItemRepository purchasesItemRepository;


    @GetMapping("/storage/store")
    public String storageMain(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        ArrayList<PrintStore> printStoresArrayList = new ArrayList<>();
        Iterable<Stores> stores = storesRepository.findAll();
        Iterator<Stores> iterator = stores.iterator();
        while (iterator.hasNext()) {
            Stores element = iterator.next();
            PrintStore e = new PrintStore();
            e.setStore_id(element.getStore_id());
            e.setStore_name(element.getStore_name());
            e.setCategory_id(element.getCategory_id());
            Categories categories = categoriesRepository.findById(e.getCategory_id()).orElseThrow();
            e.setCategory_name(categories.getCategory_name());
            printStoresArrayList.add(e);
        }
        model.addAttribute("store",printStoresArrayList);


        return "storage-store";
    }
        else {return "login";}
    }
    @GetMapping("/storage/add_store")
    public String storageAddStore(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        Iterable<Stores> stores = storesRepository.findAll();
        model.addAttribute("store", stores);
        Iterable<Categories> categories = categoriesRepository.findAll();
        model.addAttribute("categories", categories);
        return "storage-add-store";
        }
        else {return "login";}
    }

    @PostMapping("/storage/add_store")
    public String storageStoreAdd(@RequestParam Long category_id,@RequestParam String store_name, Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        Stores stores = new Stores(category_id, store_name);
        storesRepository.save(stores);
        return "redirect:/storage";
    }
        else {return "login";}
    }
}
