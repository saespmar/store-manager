package com.saespmar.storeManager.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.NaturalId;


@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;
    
    @NaturalId
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "zip_code")
    private String zipCode;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "user_state")
    private String userState;
    
    @Column(name = "country")
    private String country;
    
    @OneToMany(mappedBy="customer", fetch = FetchType.EAGER)
    private Set<UserOrder> userOrders = new HashSet<>();
    
    @OneToMany(mappedBy="customer", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ShoppingCart> inCart = new HashSet<>();
    
    @OneToMany(mappedBy="customer", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Opinion> opinions = new HashSet<>();

    public Customer() {}

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<UserOrder> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(Set<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    public Set<ShoppingCart> getInCart() {
        return inCart;
    }

    public void setInCart(Set<ShoppingCart> inCart) {
        this.inCart = inCart;
    }

    public Set<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(Set<Opinion> opinions) {
        this.opinions = opinions;
    }
    
    public void addUserOrder(UserOrder userOrder) {
        userOrders.add(userOrder);
        userOrder.setCustomer(this);
    }
    
    public void removeUserOrder(UserOrder userOrder) {
        userOrders.remove(userOrder);
        userOrder.setCustomer(null);
    }
    
    public void addToCart(Product product, int quantity) {
        ShoppingCart shoppingCart = new ShoppingCart(this, product, quantity);
        inCart.add(shoppingCart);
        product.getCarts().add(shoppingCart);
    }
    
    public void removeFromCart(Product product) {
        ShoppingCart shoppingCart = new ShoppingCart(this, product, 0);
        product.getCarts().remove(shoppingCart);
        inCart.remove(shoppingCart);
        shoppingCart.setCustomer(null);
        shoppingCart.setProduct(null);
    }
    
    public void addOpinion(Product product, int rating) {
        Opinion opinion = new Opinion(this, product, rating);
        opinions.add(opinion);
        product.getOpinions().add(opinion);
    }
    
    public void removeOpinion(Product product) {
        Opinion opinion = new Opinion(this, product, -1);
        product.getOpinions().remove(opinion);
        opinions.remove(opinion);
        opinion.setCustomer(null);
        opinion.setProduct(null);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        return email.equals(((Customer) o).getEmail());
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.email);
        return hash;
    }
    
}
