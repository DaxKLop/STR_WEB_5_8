package com.hvalinski.storage.dataBase.printInfo;

public class PrintPurchases {

    private Long purchase_id;
    private Long user_id,store_id;

    private String purchase_date, person_name;
    private Long product_id,product_count,product_price,purchases_price;
    private String product_name,image_path;

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    private String store_name;

    public Long getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(Long purchase_id) {
        this.purchase_id = purchase_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getStore_id() {
        return store_id;
    }

    public void setStore_id(Long store_id) {
        this.store_id = store_id;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getProduct_count() {
        return product_count;
    }

    public void setProduct_count(Long product_count) {
        this.product_count = product_count;
    }

    public Long getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Long product_price) {
        this.product_price = product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Long getPurchases_price() {
        return purchases_price;
    }

    public void setPurchases_price(Long purchases_price) {
        this.purchases_price = purchases_price;
    }

    public PrintPurchases(Long purchase_id, Long user_id, Long store_id, Long purchases_price, String purchase_date, String person_name, Long product_id, String store_name, Long product_count, Long product_price, String product_name, String image_path) {
        this.purchase_id = purchase_id;
        this.user_id = user_id;
        this.store_id = store_id;
        this.purchase_date = purchase_date;
        this.product_id = product_id;
        this.product_count = product_count;
        this.product_price = product_price;
        this.product_name = product_name;
        this.image_path = image_path;
        this.store_name = store_name;
        this.person_name = person_name;
        this.purchases_price = purchases_price;
    }

    public PrintPurchases() {
    }
}
