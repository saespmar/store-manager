package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.UserOrder;
import java.util.Date;

/**
 *
 * <p>This interface provides basic operations related with the UserOrder table
 * in the database.</p>
 *
 * @author saespmar
 * @version 0.0.1
 */
public interface UserOrderOps {
    
    /**
     *
     * <p>Creates a new order in the database.</p>
     * 
     * <p>The order must include a order date. The id will be automatically generated
     * by the database, so there's no need to include it in the parameters.</p>
     *
     * @param userOrder a userOrder object with a order date set.
     * @return the id of the created userOrder.
     */
    int createUserOrder(UserOrder userOrder);
    
    /**
     *
     * <p>Retrieves the order from the database.</p>
     * 
     * <p>The order will include the id, order date, shipping date, delivery date,
     * payment, customer and products fields filled.</p>
     *
     * @param id the id of the userOrder.
     * @return the userOrder model object.
     */
    UserOrder readUserOrder(int id);
    
    /**
     *
     * <p>Edits the order date of the order.</p>
     *
     * @param id the id of the userOrder.
     * @param orderDate the new order date for the order.
     */
    void updateOrderDate(int id, Date orderDate);
    
    /**
     *
     * <p>Edits the shipping date of the order.</p>
     *
     * @param id the id of the userOrder.
     * @param shippingDate the new shipping date for the order.
     */
    void updateShippingDate(int id, Date shippingDate);
    
    /**
     *
     * <p>Edits the delivery date of the order.</p>
     *
     * @param id the id of the userOrder.
     * @param deliveryDate the new delivery date for the order.
     */
    void updateDeliveryDate(int id, Date deliveryDate);
    
    /**
     *
     * <p>Edits the payment method of the order.</p>
     *
     * @param id the id of the userOrder.
     * @param payment the new payment method for the order.
     */
    void updatePayment(int id, String payment);
    
    /**
     *
     * <p>Edits the customer of the order.</p>
     *
     * @param id the id of the userOrder.
     * @param customerId the id of the new customer for the order.
     */
    void updateCustomer(int id, int customerId);
    
    /**
     *
     * <p>Adds a new product in the order.</p>
     *
     * <p>This method will also make the product point to the order.</p>
     * 
     * @param id the id of the userOrder.
     * @param productId the id of the product to be inserted.
     * @param quantity the amount of products to be inserted.
     */
    void addProduct(int id, int productId, int quantity);
    
    /**
     *
     * <p>Removes the order from the database.</p>
     * 
     * @param id the id of the userOrder.
     */
    void deleteUserOrder(int id);
}
