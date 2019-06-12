package com.saespmar.storeManager.dto;

import java.util.Date;
import java.util.HashMap;


public class OrderDTO {
    private int id;
    private Date orderDate;
    private Date shippingDate;
    private Date deliveryDate;
    private String payment;
    private HashMap<ProductDTO, Integer> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public HashMap<ProductDTO, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<ProductDTO, Integer> products) {
        this.products = products;
    }
    
}
