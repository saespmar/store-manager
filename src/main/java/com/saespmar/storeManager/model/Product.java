package com.saespmar.storeManager.model;

import java.io.Serializable;
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


@Entity
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "price")
    private float price;
    
    @Column(name = "stock")
    private int stock;
    
    @ManyToOne
    private Category category;
    
    @ManyToOne(optional = true)
    private SubProduct subProduct;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<OrderProducts> userOrders = new HashSet<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<ShoppingCart> carts = new HashSet<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<Opinion> opinions = new HashSet<>();

    public Product() {}

    public Product(String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubProduct getSubProduct() {
        return subProduct;
    }

    public void setSubProduct(SubProduct subProduct) {
        this.subProduct = subProduct;
    }

    public Set<OrderProducts> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(Set<OrderProducts> userOrders) {
        this.userOrders = userOrders;
    }

    public Set<ShoppingCart> getCarts() {
        return carts;
    }

    public void setCarts(Set<ShoppingCart> carts) {
        this.carts = carts;
    }

    public Set<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(Set<Opinion> opinions) {
        this.opinions = opinions;
    }
    
    public void addOrder(UserOrder userOrder, int quantity) {
        OrderProducts orderProducts = new OrderProducts(userOrder, this, quantity);
        userOrders.add(orderProducts);
        userOrder.getProducts().add(orderProducts);
    }
    
    public void removeOrder(UserOrder userOrder) {
        OrderProducts orderProducts = new OrderProducts(userOrder, this, 0);
        userOrder.getProducts().remove(orderProducts);
        userOrders.remove(orderProducts);
        orderProducts.setUserOrder(null);
        orderProducts.setProduct(null);
    }
    
    public void addToCart(Customer customer, int quantity) {
        ShoppingCart shoppingCart = new ShoppingCart(customer, this, quantity);
        carts.add(shoppingCart);
        customer.getInCart().add(shoppingCart);
    }
    
    public void removeFromCart(Customer customer) {
        ShoppingCart shoppingCart = new ShoppingCart(customer, this, 0);
        customer.getInCart().remove(shoppingCart);
        carts.remove(shoppingCart);
        shoppingCart.setCustomer(null);
        shoppingCart.setProduct(null);
    }
    
    public void addOpinion(Customer customer, int rating) {
        Opinion opinion = new Opinion(customer, this, rating);
        opinions.add(opinion);
        customer.getOpinions().add(opinion);
    }
    
    public void removeOpinion(Customer customer) {
        Opinion opinion = new Opinion(customer, this, -1);
        customer.getOpinions().remove(opinion);
        opinions.remove(opinion);
        opinion.setCustomer(null);
        opinion.setProduct(null);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return id == ((Product) o).getId();
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }
    
}