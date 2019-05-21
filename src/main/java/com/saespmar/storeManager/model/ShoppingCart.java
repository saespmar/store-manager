package com.saespmar.storeManager.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;


@Entity
@Table(name = "shopping_carts")
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    private Customer customer;
    
    @Id
    @ManyToOne
    private Product product;
    
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public ShoppingCart() {}

    public ShoppingCart(Customer customer, Product product, int quantity) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        if (!(o instanceof ShoppingCart)) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(customer, that.getCustomer()) &&
               Objects.equals(product, that.getProduct());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.customer);
        hash = 71 * hash + Objects.hashCode(this.product);
        return hash;
    }
    
}