package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.SubProduct;


public interface SubProductOps {
    int createSubProduct(SubProduct subProduct);
    SubProduct readSubProduct(int id);
    void updateName(int id, String name);
    void addProduct(int id, int productId);
    void removeProduct(int id, int productId);
    void deleteSubProduct(int id);  
}
