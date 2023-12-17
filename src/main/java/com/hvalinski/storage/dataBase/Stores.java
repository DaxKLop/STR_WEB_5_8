package com.hvalinski.storage.dataBase;

import jakarta.persistence.*;

@Entity
public class Stores {
    @Id
    @SequenceGenerator(name = "stor_seq", sequenceName = "stor_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "stor_seq")
    private Long store_id;
    private Long category_id;
    private String store_name;

    public Stores() {
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Stores(Long store_id, Long category_id, String store_name) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.category_id = category_id;
    }
    public Stores( Long category_id, String store_name) {
        this.store_name = store_name;
        this.category_id = category_id;
    }
    public Stores(String store_name) {
        this.store_name = store_name;
    }

    public Long getStore_id() {
        return store_id;
    }

    public void setStore_id(Long store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
}
