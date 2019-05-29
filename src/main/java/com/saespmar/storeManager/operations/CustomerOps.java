package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Customer;

/**
 *
 * <p>This interface provides basic operations related with the Customer table
 * in the database.</p>
 *
 * @author saespmar
 * @version 0.0.1
 */
public interface CustomerOps {
    
    /**
     *
     * <p>Creates a new customer in the database.</p>
     * 
     * <p>The customer must include e-mail and password. The id will be automatically generated
     * by the database, so there's no need to include it in the parameters.</p>
     *
     * @param customer a customer object with an e-mail and a password set.
     * @return the id of the created customer.
     */
    int createCustomer(Customer customer);
    
    /**
     *
     * <p>Retrieves the customer from the database.</p>
     * 
     * <p>The customer will include the id, e-mail, password, first name, last name, 
     * phone, street, zip code, city, state, country, orders and shopping cart fields 
     * filled. On the other hand, the opinions field won't be available.</p>
     *
     * @param id the id of the customer.
     * @return the customer model object.
     */
    Customer readCustomer(int id);
    
    /**
     *
     * <p>Edits the password of the customer.</p>
     * 
     * <p>This method doesn't provide a hashing algorithm. It's highly recommended
     * to encode the passwords previously, in order to avoid plain text storage of
     * this component.</p>
     *
     * @param id the id of the customer.
     * @param password the new password for the customer.
     */
    void updatePassword(int id, String password);
    
    /**
     *
     * <p>Edits the first name of the customer.</p>
     *
     * @param id the id of the customer.
     * @param firstName the new first name for the customer. It can be set to null 
     * if there isn't any.
     */
    void updateFirstName(int id, String firstName);
    
    /**
     *
     * <p>Edits the last name of the customer.</p>
     *
     * @param id the id of the customer.
     * @param lastName the new last name for the customer. It can be set to null 
     * if there isn't any.
     */
    void updateLastName(int id, String lastName);
    
    /**
     *
     * <p>Edits the phone of the customer.</p>
     *
     * @param id the id of the customer.
     * @param phone the new phone for the customer. It can be set to null if there 
     * isn't any.
     */
    void updatePhone(int id, String phone);
    
    /**
     *
     * <p>Edits the street of the customer.</p>
     *
     * @param id the id of the customer.
     * @param street the new street for the customer.  It can be set to null if 
     * there isn't any.
     */
    void updateStreet(int id, String street);
    
    /**
     *
     * <p>Edits the zip code of the customer.</p>
     *
     * @param id the id of the customer.
     * @param zipCode the new zip code for the customer. It can be set to null if 
     * there isn't any.
     */
    void updateZipCode(int id, String zipCode);
    
    /**
     *
     * <p>Edits the city of the customer.</p>
     *
     * @param id the id of the customer.
     * @param city the new city for the customer. It can be set to null if there 
     * isn't any.
     */
    void updateCity(int id, String city);
    
    /**
     *
     * <p>Edits the state of the customer.</p>
     *
     * @param id the id of the customer.
     * @param userState the new state for the customer. It can be set to null if 
     * there isn't any.
     */
    void updateState(int id, String userState);
    
    /**
     *
     * <p>Edits the country of the customer.</p>
     *
     * @param id the id of the customer.
     * @param country the new country for the customer.  It can be set to null if 
     * there isn't any.
     */
    void updateCountry(int id, String country);
    
    /**
     *
     * <p>Adds a new product in the shopping cart of the customer.</p>
     *
     * <p>This method will also make the product point to the shopping cart and
     * the shopping cart point to both customer and product.</p>
     * 
     * @param id the id of the customer.
     * @param productId the id of the product to be inserted.
     * @param quantity the amount of products to be inserted.
     */
    void addToCart(int id, int productId, int quantity);
    
    /**
     *
     * <p>Adds a new opinion written by the customer.</p>
     *
     * <p>This method will also make the product point to the opinion and
     * the opinion point to both customer and product.</p>
     * 
     * @param id the id of the customer.
     * @param productId the id of the product to be reviewed.
     * @param rating the rating of the product, between 0 and 5.
     * @param review the text of the review. It can be set to null if there isn't any.
     */
    void addOpinion(int id, int productId, int rating, String review);
    
    /**
     *
     * <p>Removes the opinion from the database.</p>
     * 
     * <p>This method will also remove the opinion on the product side.</p>
     * 
     * @param id the id of the customer.
     * @param productId the id of the product reviewed.
     */
    void removeOpinion(int id, int productId);
    
    /**
     *
     * <p>Removes the customer from the database.</p>
     * 
     * @param id the id of the customer.
     */
    void deleteCustomer(int id);
}
