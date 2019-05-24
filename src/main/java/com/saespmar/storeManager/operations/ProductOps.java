package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Product;


public interface ProductOps {
    int createProduct(Product product);
    Product readProduct(int id);
    void updateName(int id, String name);
    void updateDescription(int id, String description);
    void updatePrice(int id, float price);
    void updateStock(int id, int stock);
    void updateCategory(int id, int categoryId);
    void updateSubProduct(int id, int subProductId);
    void addToOrder(int id, int order_id, int quantity);
    void removeFromOrder(int id, int order_id);
    void addToCart(int id, int customer_id, int quantity);
    void removeFromCart(int id, int customer_id);
    void addOpinion(int id, int customer_id, int rating, String review);
    void deleteProduct(int id);
}
