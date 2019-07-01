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
        // Check input values
        if (id < 1) return null;
        
        // Transform all the Product models into DTO 
        Category category = categoryOps.readCategory(id);
        Set<Product> products = category.getProducts();
        Set<ProductDTO> productsDTO = new HashSet<>();
        for (Product p : products) {
            productsDTO.add(ServiceUtils.productTransform(p));
        }
        return productsDTO;
    }
    
    public static ProductDTO getProduct(int id){
        // Check input values
        if (id < 1) return null;
        
        Product p = productOps.readProduct(id);        
        return ServiceUtils.productTransform(p);
    }
    
    public static Set<OpinionDTO> getOpinions(int id){
        // Check input values
        if (id < 1) return null;
        
        // All the opinions need an associated ProductDTO
        Product p = productOps.readProduct(id);
        ProductDTO pdto = ServiceUtils.productTransform(p);
        
        // Transform models into DTO 
        Set<Opinion> opinions = p.getOpinions();
        Set<OpinionDTO> opinionsDTO = new HashSet<>();
        for (Opinion o : opinions) {
            opinionsDTO.add(ServiceUtils.opinionTransform(o, pdto));
        }
        
        return opinionsDTO;
    }
    
    public static CustomerDTO registerUser(String email, String password){
        // Check input values
        if (email == null || password == null) return null;
        if (!validMail(email)) return null;
        if (password.length() < 8 || password.length() > 30) return null;
        
        // Check if the password has an uppercase letter, a lowercase letter and a number
        boolean up = false, low = false, num = false;
        for (int i = 0; i < password.length() && !ServiceUtils.securePassword(up, low, num); i++){
            char current = password.charAt(i);
            if (!up && Character.isUpperCase(current)) up = true;
            else if (!low && Character.isLowerCase(current)) low = true;
            else if (!num && Character.isDigit(current)) num = true;
        }
        if (!ServiceUtils.securePassword(up, low, num)) return null;
        
        // Hash the password
        String hashedPassword = ServiceUtils.hashPassword(password);
        
        // Add the customer to the database
        if (customerOps.searchCustomer(email) == null){
            int id = customerOps.createCustomer(new Customer(email, hashedPassword));
            CustomerDTO cdto = new CustomerDTO();
            cdto.setEmail(email);
            cdto.setId(id);
            return cdto;
        } else {
            return null;
        }
    }
    
    public static CustomerDTO logIn(String email, String password){
        // Check input values
        if (email == null || password == null) return null;
        
        // Check if the customer exists and the password is correct
        Customer c = customerOps.searchCustomer(email);
        if (c != null && c.getPassword().equals(ServiceUtils.hashPassword(password))){
            return ServiceUtils.customerTransform(c);
        } else {
            return null;
        }
    }
    
    private static boolean validMail(String email){
        if (email == null) return false;
        if (email.length() > 50) return false;
        if (email.indexOf('@') == -1) return false;
        String name = email.substring(0, email.indexOf('@')); // The string after the @
        String domain = email.substring(email.indexOf('@')+1, email.length());
        if (domain.indexOf('.') == -1) return false;
        String server = domain.substring(0, domain.indexOf('.')); // The string between the @ and the .
        String tld = domain.substring(domain.indexOf('.'), domain.length()); // The string after the .
        return name.length() > 0 && server.length() > 0 && tld.length() > 0;
    }
    
}
