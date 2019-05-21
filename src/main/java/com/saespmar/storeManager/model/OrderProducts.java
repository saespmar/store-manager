package com.saespmar.storeManager.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;


@Entity
@Table(name = "orders_have_products")
public class OrderProducts implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    private UserOrder userOrder;
    
    @Id
    @ManyToOne
    private Product product;
    
    @Column(name = "quantity", nullable = false)
    private int quantity;
    
    public OrderProducts() {}
    
    public OrderProducts(UserOrder userOrder, Product product, int quantity) {
        this.userOrder = userOrder;
        this.product = product;
        this.quantity = quantity;
    }
    
    public UserOrder getUserOrder() {
        return userOrder;
    }
    
    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProducts)) return false;
        OrderProducts that = (OrderProducts) o;
        return Objects.equals(userOrder, that.getUserOrder()) &&
                Objects.equals(product, that.getProduct());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.userOrder);
        hash = 67 * hash + Objects.hashCode(this.product);
        return hash;
    }
    
}