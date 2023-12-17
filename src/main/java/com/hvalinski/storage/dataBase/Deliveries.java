package com.hvalinski.storage.dataBase;

import jakarta.persistence.*;

@Entity
public class Deliveries {
    @Id
    @SequenceGenerator(name = "del_seq", sequenceName = "del_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "del_seq")
    private Long deliveries_id;
    private Long product_id, user_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Deliveries(Long product_id, Long product_count,  Long user_id, Long total_price, String delivery_date, String address) {
        this.product_id = product_id;
        this.product_count = product_count;
        this.total_price = total_price;
        this.delivery_date = delivery_date;
        this.address = address;
        this.user_id = user_id;
    }

    public Deliveries() {
    }

    public Long getDeliveries_id() {
        return deliveries_id;
    }

    public void setDeliveries_id(Long deliveries_id) {
        this.deliveries_id = deliveries_id;
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

    public Long getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Long total_price) {
        this.total_price = total_price;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Long  product_count, total_price;
    private String delivery_date, address;

}
