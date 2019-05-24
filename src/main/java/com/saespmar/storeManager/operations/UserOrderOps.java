package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.UserOrder;
import java.util.Date;


public interface UserOrderOps {
    int createUserOrder(UserOrder userOrder);
    UserOrder readUserOrder(int id);
    void updateOrderDate(int id, Date orderDate);
    void updateShippingDate(int id, Date shippingDate);
    void updateDeliveryDate(int id, Date deliveryDate);
    void updatePayment(int id, String payment);
    void updateCustomer(int id, int customerId);
    void addProduct(int id, int product_id, int quantity);
    void deleteUserOrder(int id);
}
