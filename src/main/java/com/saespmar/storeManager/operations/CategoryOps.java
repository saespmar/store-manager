package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Category;


public interface CategoryOps {
    int createCategory(Category category);
    Category readCategory(int id);
    void updateName(int id, String name);
    void addProduct(int id, int productId);
    void removeProduct(int id, int productId);
    void deleteCategory(int id); 
}
