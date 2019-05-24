package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Customer;


public interface CustomerOps {
    int createCustomer(Customer customer);
    Customer readCustomer(int id);
    void updatePassword(int id, String password);
    void updateFirstName(int id, String firstName);
    void updateLastName(int id, String lastName);
    void updatePhone(int id, String phone);
    void updateStreet(int id, String street);
    void updateZipCode(int id, String zipCode);
    void updateCity(int id, String city);
    void updateState(int id, String userState);
    void updateCountry(int id, String country);
    void addToCart(int id, int product_id, int quantity);
    void addOpinion(int id, int product_id, int rating, String review);
    void removeOpinion(int id, int product_id);
    void deleteCustomer(int id);
}
