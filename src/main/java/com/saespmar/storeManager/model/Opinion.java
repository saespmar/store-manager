package com.saespmar.storeManager.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;


@Entity
@Table(name = "opinions")
public class Opinion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    private Customer customer;
    
    @Id
    @ManyToOne
    private Product product;
    
    @Column(name = "rating", nullable = false)
    private int rating;
    
    @Column(name = "review")
    private String review;

    public Opinion() {}
    
    public Opinion(Customer customer, Product product, int rating, String review) {
        this.customer = customer;
        this.product = product;
        this.rating = rating;
        this.review = review;
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
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getReview() {
        return review;
    }
    
    public void setReview(String review) {
        this.review = review;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Opinion)) return false;
        Opinion that = (Opinion) o;
        return Objects.equals(customer, that.getCustomer()) &&
                Objects.equals(product, that.getProduct());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.customer);
        hash = 53 * hash + Objects.hashCode(this.product);
        return hash;
    }
    
}