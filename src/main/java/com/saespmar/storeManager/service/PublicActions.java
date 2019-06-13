package com.saespmar.storeManager.service;

import com.saespmar.storeManager.dto.*;
import com.saespmar.storeManager.model.*;
import com.saespmar.storeManager.operations.*;
import java.util.HashSet;
import java.util.Set;



public class PublicActions {
    
    static CategoryOps categoryOps = new CategoryOpsImpl();
    static CustomerOps customerOps = new CustomerOpsImpl();
    static ProductOps productOps = new ProductOpsImpl();
    static SubProductOps subProductOps = new SubProductOpsImpl();
    static UserOrderOps userOrderOps = new UserOrderOpsImpl();
    
    
    public static Set<ProductDTO> getProductsFromCategory(int id) {
        if (id < 1) return null;
        Category category = categoryOps.readCategory(id);
        Set<Product> products = category.getProducts();
        Set<ProductDTO> productsDTO = new HashSet<>();
        
        // Transform model into DTO
        for (Product p : products) {
            ProductDTO pdto = new ProductDTO();
            pdto.setCategory(category.getName());
            pdto.setDescription(p.getDescription());
            pdto.setId(p.getId());
            pdto.setName(p.getName());
            pdto.setPrice(p.getPrice());
            pdto.setStock(p.getStock());
            if (p.getSubProduct() != null)
                pdto.setSubProduct(p.getSubProduct().getName());
            
            productsDTO.add(pdto);
        }
        return productsDTO;
    }

    public static ProductDTO getProduct(int id){
        if (id < 1) return null;
        Product p = productOps.readProduct(id);
        ProductDTO pdto = new ProductDTO();
        pdto.setDescription(p.getDescription());
        pdto.setId(p.getId());
        pdto.setName(p.getName());
        pdto.setPrice(p.getPrice());
        pdto.setStock(p.getStock());
        if (p.getCategory() != null)
            pdto.setCategory(p.getCategory().getName());
        if (p.getSubProduct() != null)
            pdto.setSubProduct(p.getSubProduct().getName());
        
        return pdto;
    }

	public static Set<OpinionDTO> getOpinions(int id){
        if (id < 1) return null;
        Product p = productOps.readProduct(id);
        ProductDTO pdto = new ProductDTO();
        pdto.setDescription(p.getDescription());
        pdto.setId(p.getId());
        pdto.setName(p.getName());
        pdto.setPrice(p.getPrice());
        pdto.setStock(p.getStock());
        if (p.getCategory() != null)
            pdto.setCategory(p.getCategory().getName());
        if (p.getSubProduct() != null)
            pdto.setSubProduct(p.getSubProduct().getName());
        
        Set<Opinion> opinions = p.getOpinions();
        Set<OpinionDTO> opinionsDTO = new HashSet<>();
        
        // Transform model into DTO
        for (Opinion o : opinions) {
            OpinionDTO odto = new OpinionDTO();
            odto.setCustomer(o.getCustomer().getEmail());
            odto.setProduct(pdto);
            odto.setRating(o.getRating());
            if (o.getReview() != null)
                odto.setReview(o.getReview());
            
            opinionsDTO.add(odto);
        }
        
        return opinionsDTO;
    }
}
