package com.hvalinski.storage.dataBase;

import jakarta.persistence.*;

@Entity
public class Products {
    @Id
    @SequenceGenerator(name = "prod_seq", sequenceName = "prod_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "prod_seq")
    private Long product_id;
    private Long category_id;
    private String product_name,image_path;
    private String description;
    private Long product_count;
    private Long product_price;

    public Long getProduct_count() {
        return product_count;
    }

    public void setProduct_count(Long product_count) {
        this.product_count = product_count;
    }

    public Products(String product_name) {
        this.product_name = product_name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Products(Long category_id, String product_name, String description, Long product_count, Long product_price) {
        this.category_id = category_id;
        this.product_name = product_name;
        this.description = description;
        this.product_count = product_count;
        this.product_price = product_price;
    }

    public Long getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Long product_price) {
        this.product_price = product_price;
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

    public Products(Long product_id, Long category_id, String product_name) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.product_name = product_name;
    }

    public Products() {
    }
}
