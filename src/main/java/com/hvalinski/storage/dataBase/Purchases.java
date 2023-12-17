package com.hvalinski.storage.dataBase;

import jakarta.persistence.*;

@Entity
public class Purchases {

    @Id
    @SequenceGenerator(name = "pur3_seq", sequenceName = "pur3_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pur3_seq")
    private Long purchase_id;
    private Long user_id,store_id;
    private String purchase_date;

    public Long getPurchase_id() {
        return purchase_id;
    }

    public Purchases(Long user_id, Long store_id, String purchase_date) {
        this.user_id = user_id;
        this.store_id = store_id;
        this.purchase_date = purchase_date;
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

    public Purchases(Long store_id, String purchase_date) {
        this.store_id = store_id;
        this.purchase_date = purchase_date;
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

    public Purchases(Long purchase_id, Long user_id, Long store_id, String purchase_date) {
        this.purchase_id = purchase_id;
        this.user_id = user_id;
        this.store_id = store_id;
        this.purchase_date = purchase_date;
    }

    public Purchases() {
    }
}
