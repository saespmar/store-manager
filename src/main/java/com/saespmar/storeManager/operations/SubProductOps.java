package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.SubProduct;

/**
 *
 * <p>This interface provides basic operations related with the SubProduct table
 * in the database.</p>
 *
 * @author saespmar
 * @version 0.0.1
 */
public interface SubProductOps {
    
    /**
     *
     * <p>Creates a new subproduct in the database.</p>
     * 
     * <p>The subproduct must include a name. The id will be automatically generated
     * by the database, so there's no need to include it in the parameters.</p>
     *
     * @param subProduct a subproduct object with a name set.
     * @return the id of the created subproduct.
     */
    int createSubProduct(SubProduct subProduct);
    
    /**
     *
     * <p>Retrieves the subproduct from the database.</p>
     * 
     * <p>The subproduct will include the id, name and products fields filled.</p>
     *
     * @param id the id of the subproduct.
     * @return the subproduct model object.
     */
    SubProduct readSubProduct(int id);
    
    /**
     *
     * <p>Edits the name of the subproduct.</p>
     *
     * @param id the id of the subproduct.
     * @param name the new name for the subproduct.
     */
    void updateName(int id, String name);
    
     /**
     *
     * <p>Adds a new product in the subproduct.</p>
     *
     * <p>This method will also make the product point to the subproduct.</p>
     * 
     * @param id the id of the subproduct.
     * @param productId the id of the product to be inserted.
     */
    void addProduct(int id, int productId);
    
    /**
     *
     * <p>Removes an existing product from the subproduct.</p>
     *
     * <p>This method will also set the subproduct of the product to null.</p>
     * 
     * @param id the id of the subproduct.
     * @param productId the id of the product to be removed.
     */
    void removeProduct(int id, int productId);
    
    /**
     *
     * <p>Removes the subproduct from the database.</p>
     * 
     * @param id the id of the subproduct.
     */
    void deleteSubProduct(int id);  
}
