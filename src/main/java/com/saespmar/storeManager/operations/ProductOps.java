package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Product;

/**
 *
 * <p>This interface provides basic operations related with the Product table
 * in the database.</p>
 *
 * @author saespmar
 * @version 0.0.1
 */
public interface ProductOps {
    
    /**
     *
     * <p>Creates a new product in the database.</p>
     * 
     * <p>The product must include a name. The id will be automatically generated
     * by the database, so there's no need to include it in the parameters.</p>
     *
     * @param product a product object with a name set.
     * @return the id of the created product.
     */
    int createProduct(Product product);
    
    /**
     *
     * <p>Retrieves the product from the database.</p>
     * 
     * <p>The product will include the id, name, description, price, stock, category,
     * subproduct and opinions fields filled. On the other hand, the orders and carts
     * fields won't be available.</p>
     *
     * @param id the id of the product.
     * @return the product model object.
     */
    Product readProduct(int id);
    
    /**
     *
     * <p>Edits the name of the product.</p>
     *
     * @param id the id of the product.
     * @param name the new name for the product.
     */
    void updateName(int id, String name);
    
    /**
     *
     * <p>Edits the description of the product.</p>
     *
     * @param id the id of the product.
     * @param description the new description for the product.
     */
    void updateDescription(int id, String description);
    
    /**
     *
     * <p>Edits the price of the product.</p>
     *
     * @param id the id of the product.
     * @param price the new price for the product.  It can be set to null if there 
     * isn't any.
     */
    void updatePrice(int id, float price);
    
    /**
     *
     * <p>Edits the stock of the product.</p>
     *
     * @param id the id of the product.
     * @param stock the new stock for the product.
     */
    void updateStock(int id, int stock);
    
    /**
     *
     * <p>Edits the category of the product.</p>
     *
     * @param id the id of the product.
     * @param categoryId the id of the new category for the product.
     */
    void updateCategory(int id, int categoryId);
    
    /**
     *
     * <p>Edits the subproduct of the product.</p>
     *
     * @param id the id of the product.
     * @param subProductId the id of the new subproduct for the product.
     */
    void updateSubProduct(int id, int subProductId);
    
    /**
     *
     * <p>Adds a new order item containing the product.</p>
     *
     * <p>This method will also make the customer point to the order and the order 
     * point to both customer and product.</p>
     * 
     * @param id the id of the product to be added.
     * @param orderId the id of the order.
     * @param quantity the amount of products to be inserted.
     */
    void addToOrder(int id, int orderId, int quantity);
    
    /**
     *
     * <p>Removes the product from an order.</p>
     * 
     * <p>This method will also remove the product on the order side.</p>
     * 
     * @param id the id of the product.
     * @param orderId the id of the order that has the product.
     */
    void removeFromOrder(int id, int orderId);
    
    /**
     *
     * <p>Adds a new product in the shopping cart of a customer.</p>
     *
     * <p>This method will also make the customer point to the shopping cart and
     * the shopping cart point to both customer and product.</p>
     * 
     * @param id the id of the product to be inserted.
     * @param customerId the id of the customer that owns the cart.
     * @param quantity the amount of products to be inserted.
     */
    void addToCart(int id, int customerId, int quantity);
    
    /**
     *
     * <p>Removes the product from a shopping cart.</p>
     * 
     * <p>This method will also remove the product on the customer side.</p>
     * 
     * @param id the id of the product.
     * @param customerId the id of the customer that owns the cart.
     */
    void removeFromCart(int id, int customerId);
    
    /**
     *
     * <p>Adds a new opinion for the product.</p>
     *
     * <p>This method will also make the customer point to the opinion and
     * the opinion point to both customer and product.</p>
     * 
     * @param id the id of the product to be reviewed.
     * @param customerId the id of the customer.
     * @param rating the rating of the product, between 0 and 5.
     * @param review the text of the review. It can be set to null if there isn't any.
     */
    void addOpinion(int id, int customerId, int rating, String review);
    
    /**
     *
     * <p>Removes the product from the database.</p>
     * 
     * @param id the id of the product.
     */
    void deleteProduct(int id);
}
