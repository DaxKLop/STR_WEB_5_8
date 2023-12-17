package com.hvalinski.storage.dataBase.printInfo;

public class PrintDeliveries {
    private Long deliveries_id, product_id;
    private Long  product_count, total_price;
    private String delivery_date, address;

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    private String product_name,image_path;
    private String person_name;

    public Long getDeliveries_id() {
        return deliveries_id;
    }

    public void setDeliveries_id(Long deliveries_id) {
        this.deliveries_id = deliveries_id;
    }

    public PrintDeliveries(Long deliveries_id, Long product_count, Long product_id,Long total_price, String delivery_date, String address, String product_name, String image_path, String person_name) {
        this.deliveries_id = deliveries_id;
        this.product_id = product_id;
        this.product_count = product_count;
        this.total_price = total_price;
        this.delivery_date = delivery_date;
        this.address = address;
        this.product_name = product_name;
        this.image_path = image_path;
        this.person_name = person_name;
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

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public PrintDeliveries() {
    }
}
