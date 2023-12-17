package com.hvalinski.storage.controllers;

import com.hvalinski.storage.dataBase.printInfo.PrintDeliveries;
import com.hvalinski.storage.dataBase.printInfo.PrintProduct;
import com.hvalinski.storage.dataBase.printInfo.PrintPurchases;
import com.hvalinski.storage.dataBase.printInfo.PrintStore;
import com.hvalinski.storage.dataBase.*;
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
public class PurchasesController {
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
    @Autowired
    private PurchaseRepository purchasesRepository;
    @Autowired
    private PurchaseItemRepository purchasesItemRepository;

    @GetMapping("/purchases")
    public String purchasesMain(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        ArrayList<PrintDeliveries> printDeliveriesArrayList = new ArrayList<>();
        Iterable<Deliveries> deliveries = deliveriesRepository.findAll();
        Iterator<Deliveries> iterator = deliveries.iterator();
        while (iterator.hasNext()) {
            Deliveries element = iterator.next();
            PrintDeliveries e = new PrintDeliveries();
            e.setDeliveries_id(element.getDeliveries_id());
            if(element.getUser_id() == AuthorizationController.authorizedUser.getUser_id()) {
                e.setProduct_id(element.getProduct_id());
                e.setDelivery_date(element.getDelivery_date());
                e.setAddress(element.getAddress());
                e.setProduct_count(element.getProduct_count());
                e.setTotal_price(element.getTotal_price());
                Products products = productRepository.findById(element.getProduct_id()).orElseThrow();
                e.setProduct_name(products.getProduct_name());
                e.setImage_path(products.getImage_path());
                Person person = personRepository.findById(element.getUser_id()).orElseThrow();
                e.setPerson_name(person.getPerson_name());
                printDeliveriesArrayList.add(e);
            }
        }
        if (!printDeliveriesArrayList.isEmpty()) {
            model.addAttribute("delivery", printDeliveriesArrayList);
        }
        return "storage-delivery";
        }
        else {return "login";}
    }

    @GetMapping("/delivery_admin")
    public String adminAdmin(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
            ArrayList<PrintDeliveries> printDeliveriesArrayList = new ArrayList<>();
            Iterable<Deliveries> deliveries = deliveriesRepository.findAll();
            Iterator<Deliveries> iterator = deliveries.iterator();
            while (iterator.hasNext()) {
                Deliveries element = iterator.next();
                PrintDeliveries e = new PrintDeliveries();
                e.setDeliveries_id(element.getDeliveries_id());
                e.setProduct_id(element.getProduct_id());
                e.setDelivery_date(element.getDelivery_date());
                e.setAddress(element.getAddress());
                e.setProduct_count(element.getProduct_count());
                e.setTotal_price(element.getTotal_price());
                Products products = productRepository.findById(element.getProduct_id()).orElseThrow();
                e.setProduct_name(products.getProduct_name());
                e.setImage_path(products.getImage_path());
                Person person = personRepository.findById(element.getUser_id()).orElseThrow();
                e.setPerson_name(person.getPerson_name());
                printDeliveriesArrayList.add(e);
                }
            if (!printDeliveriesArrayList.isEmpty()) {
                model.addAttribute("delivery", printDeliveriesArrayList);
            }
            return "storage-delivery-admin";
        }
        else {return "login";}
    }

    @GetMapping("/purchases_admin")
    public String purchasesAdmin(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
            ArrayList<PrintPurchases> printPurchasesArrayList = new ArrayList<>();
            Iterable<Purchases_Item> purchases_items = purchasesItemRepository.findAll();
            Iterator<Purchases_Item> iterator = purchases_items.iterator();
            while (iterator.hasNext()) {
                Purchases_Item element = iterator.next();
                PrintPurchases e = new PrintPurchases();
                e.setProduct_id(element.getProduct_id());
                e.setPurchases_price(element.getPurchases_price());
                e.setPurchase_id(element.getPurchase_id());
                e.setProduct_count(element.getProduct_count());
                Purchases purchases = purchasesRepository.findById(e.getPurchase_id()).orElseThrow();
                e.setUser_id(purchases.getUser_id());
                e.setStore_id(purchases.getStore_id());
                e.setPurchase_date(purchases.getPurchase_date());
                Products products = productRepository.findById(e.getProduct_id()).orElseThrow();
                e.setProduct_name(products.getProduct_name());
                e.setImage_path(products.getImage_path());
                Stores stores = storesRepository.findById(e.getStore_id()).orElseThrow();
                e.setStore_name(stores.getStore_name());
                printPurchasesArrayList.add(e);
                Person person = personRepository.findById(e.getUser_id()).orElseThrow();
                e.setPerson_name(person.getPerson_name());
            }
            model.addAttribute("purchases",printPurchasesArrayList);
            return "storage-purchases";
        }
        else {return "login";}
    }

    @GetMapping("/purchases/add_purchases/{id}/stores")
    public String purchasesAdd(@PathVariable(value = "id") Long id, Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users", users);
        Iterable<Purchases> purchases = purchasesRepository.findAll();
        model.addAttribute("purchases", purchases);
        Iterable<Purchases_Item> purchases_items = purchasesItemRepository.findAll();
        model.addAttribute("purchases_items", purchases_items);
        Iterable<Products> products = productRepository.findAll();
        model.addAttribute("products", products);
        Optional<Stores> stores = storesRepository.findById(id);
        PrintStore printStore = new PrintStore();
        printStore.setCategory_id(stores.get().getCategory_id());
        printStore.setCategory_name(stores.get().getStore_name());
        model.addAttribute("stores", printStore);
        return "storage-add-purchases";
    }
        else {return "login";}
        }


    @GetMapping("/purchases/add_purchases/{id}")
    public String purchases(@PathVariable(value = "id") Long id, Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
            Optional<Products> products = productRepository.findById(id);
            PrintProduct printProduct = new PrintProduct();
            printProduct.setProduct_id(products.get().getProduct_id());
            printProduct.setProduct_name(products.get().getProduct_name());
            printProduct.setCategory_id(products.get().getCategory_id());
            printProduct.setDescription(products.get().getDescription());
            printProduct.setProduct_count(products.get().getProduct_count());
            printProduct.setProduct_price(products.get().getProduct_price());

            model.addAttribute("product", printProduct);
            return "storage-add-deliveries";
        }
        else {return "login";}
    }

    @PostMapping("/purchases/add_purchases/{id}")
    public String purchasesAdd(@PathVariable(value = "id") Long id, @RequestParam Long product_count,@RequestParam String delivery_date,@RequestParam String address, Model model) {
        Products products = productRepository.findById(id).orElseThrow();
        products.setProduct_count(products.getProduct_count() - product_count);
        Deliveries deliveries = new Deliveries(id, product_count, AuthorizationController.authorizedUser.getUser_id(),products.getProduct_price() * product_count,  delivery_date, address);
        deliveriesRepository.save(deliveries);
        return "redirect:/";
    }


        @PostMapping("/purchases/add_purchases/{id}/stores")
        public String purchases(@PathVariable(value = "id") Long id, @RequestParam Long product_id, @RequestParam Long product_count,@RequestParam String purchase_date, Model model) {
        Products products = productRepository.findById(product_id).orElseThrow();
        products.setProduct_count(products.getProduct_count() + product_count);
        Purchases purchases = new Purchases(AuthorizationController.authorizedUser.getUser_id(),id,purchase_date);
        purchasesRepository.save(purchases);
            System.out.println(purchases.getPurchase_id());
            System.out.println(product_id);
            System.out.println(product_count);
            System.out.println(products.getProduct_price() * product_count);
            Purchases_Item purchases_item = new Purchases_Item(purchases.getPurchase_id(),product_id, product_count, products.getProduct_price() * product_count);
            purchasesItemRepository.save(purchases_item);
            return "redirect:/purchases";
        }

}

