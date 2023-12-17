package com.hvalinski.storage.dataBase;

import jakarta.persistence.*;

@Entity
public class Purchases_Item {
    @Id
    @SequenceGenerator(name = "pur_item_seq1", sequenceName = "pur_item_sequence1", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pur_item_seq1")
    private Long purchase_item_id;
    private Long purchase_id,product_id,product_count,purchases_price;

    public Long getPurchase_item_id() {
        return purchase_item_id;
    }

    public void setPurchase_item_id(Long purchase_item_id) {
        this.purchase_item_id = purchase_item_id;
    }

    public Purchases_Item(Long purchase_item_id, Long purchase_id, Long product_id, Long product_count, Long purchases_price) {
        this.purchase_item_id = purchase_item_id;
        this.purchase_id = purchase_id;
        this.product_id = product_id;
        this.product_count = product_count;
        this.purchases_price = purchases_price;
    }




    public Long getPurchases_price() {
        return purchases_price;
    }

    public void setPurchases_price(Long purchases_price) {
        this.purchases_price = purchases_price;
    }

    public Long getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(Long purchase_id) {
        this.purchase_id = purchase_id;
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


    public Long getPurchase_price() {
        return purchases_price;
    }

    public void setPurchase_price(Long purchase_price) {
        this.purchases_price = purchase_price;
    }

    public Purchases_Item(Long purchase_id, Long product_id, Long product_count, Long purchase_price) {
        this.purchase_id = purchase_id;
        this.product_id = product_id;
        this.product_count = product_count;
        this.purchases_price = purchase_price;
    }
    public Purchases_Item(Long product_id, Long product_count, Long purchases_price) {
        this.product_id = product_id;
        this.product_count = product_count;
        this.purchases_price = purchases_price;
    }
    public Purchases_Item() {
    }
}
