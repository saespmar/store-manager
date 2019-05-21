package com.saespmar.storeManager.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "user_orders")
public class UserOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;
    
    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    
    @Column(name = "shipping_date")
    @Temporal(TemporalType.DATE)
    private Date shippingDate;
    
    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    
    @Column(name = "payment")
    private String payment;
    
    @ManyToOne
    private Customer customer;
    
    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.PERSIST)
    private Set<OrderProducts> products = new HashSet<>();

    public UserOrder() {}

    public UserOrder(Date orderDate) {
        this.orderDate = orderDate;
    }
    
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
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Set<OrderProducts> getProducts() {
        return products;
    }
    
    public void setProducts(Set<OrderProducts> products) {
        this.products = products;
    }
    
    public void addProduct(Product product, int quantity) {
        OrderProducts orderProducts = new OrderProducts(this, product, quantity);
        products.add(orderProducts);
        product.getUserOrders().add(orderProducts);
    }
    
    public void removeProduct(Product product) {
        OrderProducts orderProducts = new OrderProducts(this, product, 0);
        product.getUserOrders().remove(orderProducts);
        products.remove(orderProducts);
        orderProducts.setUserOrder(null);
        orderProducts.setProduct(null);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOrder)) return false;
        return id == ((UserOrder) o).getId();
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        return hash;
    }
    
}