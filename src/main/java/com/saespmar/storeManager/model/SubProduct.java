package com.saespmar.storeManager.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;


@Entity
@Table(name = "sub_products")
public class SubProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @OneToMany(mappedBy="subProduct")
    private Set<Product> products = new HashSet<>();
    
    public SubProduct() {}

    public SubProduct(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<Product> getProducts() {
        return products;
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    
    public void addProduct(Product product) {
        products.add(product);
        product.setSubProduct(this);
    }
    
    public void removeProduct(Product product) {
        products.remove(product);
        product.setSubProduct(null);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubProduct)) return false;
        return id == ((SubProduct) o).getId();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }
    
}