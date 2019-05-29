package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Category;

/**
 *
 * <p>This interface provides basic operations related with the Category table
 * in the database.</p>
 *
 * @author saespmar
 * @version 0.0.1
 */
public interface CategoryOps {
    
    /**
     *
     * <p>Creates a new category in the database.</p>
     * 
     * <p>The category must include a name. The id will be automatically generated
     * by the database, so there's no need to include it in the parameters.</p>
     *
     * @param category a category object with a name set.
     * @return the id of the created category.
     */
    int createCategory(Category category);
    
    /**
     *
     * <p>Retrieves the category from the database.</p>
     * 
     * <p>The category will include the id, name and products fields filled.</p>
     *
     * @param id the id of the category.
     * @return the category model object.
     */
    Category readCategory(int id);
    
    /**
     *
     * <p>Edits the name of the category.</p>
     *
     * @param id the id of the category.
     * @param name the new name for the category.
     */
    void updateName(int id, String name);
    
    /**
     *
     * <p>Adds a new product in the category.</p>
     *
     * <p>This method will also make the product point to the category.</p>
     * 
     * @param id the id of the category.
     * @param productId the id of the product to be inserted.
     */
    void addProduct(int id, int productId);
    
    /**
     *
     * <p>Removes an existing product from the category.</p>
     *
     * <p>This method will also set the category of the product to null.</p>
     * 
     * @param id the id of the category.
     * @param productId the id of the product to be removed.
     */
    void removeProduct(int id, int productId);
    
    /**
     *
     * <p>Removes the category from the database.</p>
     * 
     * @param id the id of the category.
     */
    void deleteCategory(int id);
}
