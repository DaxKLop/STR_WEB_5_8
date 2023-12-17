package com.hvalinski.storage.dataBase;


import jakarta.persistence.*;

@Entity
public class Categories {
    @Id
    @SequenceGenerator(name = "cat_seq", sequenceName = "cat_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cat_seq")
        private Long category_id;
        private String category_name,image_path;

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Categories(String category_name) {
        this.category_name = category_name;
    }

    public Categories(String category_name, String image_path) {
        this.category_name = category_name;
        this.image_path = image_path;
    }

    public Categories() {
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}

