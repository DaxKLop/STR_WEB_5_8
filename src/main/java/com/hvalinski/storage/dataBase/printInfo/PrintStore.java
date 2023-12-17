package com.hvalinski.storage.dataBase.printInfo;

public class PrintStore {
    private Long store_id;
    private Long category_id;
    private String store_name;
    private String category_name;

    public Long getStore_id() {
        return store_id;
    }

    public void setStore_id(Long store_id) {
        this.store_id = store_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public PrintStore(Long store_id, Long category_id, String store_name, String category_name) {
        this.store_id = store_id;
        this.category_id = category_id;
        this.store_name = store_name;
        this.category_name = category_name;
    }

    public PrintStore() {
    }
}
