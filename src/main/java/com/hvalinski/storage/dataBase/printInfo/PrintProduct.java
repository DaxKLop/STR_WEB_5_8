package com.hvalinski.storage.dataBase.printInfo;

public class PrintProduct {

    private Long product_id, category_id, product_count, product_price;
    private String product_name;
    private String category_name;

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

    public PrintProduct(Long product_id, Long category_id, String product_name, String category_name, String description, String image_path, Long product_count, Long product_price) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.product_name = product_name;
        this.category_name = category_name;
        this.description = description;
        this.image_path = image_path;
        this.product_count = product_count;
        this.product_price = product_price;
    }

    public PrintProduct() {
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    private String description;
    private String image_path;
}
