package com.saespmar.storeManager.service;

import com.saespmar.storeManager.dto.*;
import com.saespmar.storeManager.exception.*;
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
    
    
    public static Set<ProductDTO> getProductsFromCategory(int id) throws CategoryNotFoundException {
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        Category category = categoryOps.readCategory(id);
        if (category == null) throw new CategoryNotFoundException("Category with ID " + id + " not found");
        
        // Transform all the Product models into DTO 
        Set<Product> products = category.getProducts();
        Set<ProductDTO> productsDTO = new HashSet<>();
        for (Product p : products) {
            productsDTO.add(ServiceUtils.productTransform(p));
        }
        return productsDTO;
    }
    
    public static ProductDTO getProduct(int id) throws ProductNotFoundException{
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        
        Product p = productOps.readProduct(id); 
        if (p == null) throw new ProductNotFoundException("Product with ID " + id + " not found");
        return ServiceUtils.productTransform(p);
    }
    
    public static Set<OpinionDTO> getOpinions(int id) throws ProductNotFoundException{
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        
        // All the opinions need an associated ProductDTO
        Product p = productOps.readProduct(id);
        if (p == null) throw new ProductNotFoundException("Product with ID " + id + " not found");
        ProductDTO pdto = ServiceUtils.productTransform(p);
        
        // Transform models into DTO 
        Set<Opinion> opinions = p.getOpinions();
        Set<OpinionDTO> opinionsDTO = new HashSet<>();
        for (Opinion o : opinions) {
            opinionsDTO.add(ServiceUtils.opinionTransform(o, pdto));
        }
        
        return opinionsDTO;
    }
    
    public static CustomerDTO registerUser(String email, String password) throws DuplicatedCustomerException{
        // Check input values
        if (email == null || password == null) throw new IllegalArgumentException("Parameters can't be null");
        if (!validMail(email)) throw new IllegalArgumentException("Invalid email");
        if (password.length() < 8 || password.length() > 30) throw new IllegalArgumentException("Incorrect password length");
        
        // Check if the password has an uppercase letter, a lowercase letter and a number
        boolean up = false, low = false, num = false;
        for (int i = 0; i < password.length() && !ServiceUtils.securePassword(up, low, num); i++){
            char current = password.charAt(i);
            if (!up && Character.isUpperCase(current)) up = true;
            else if (!low && Character.isLowerCase(current)) low = true;
            else if (!num && Character.isDigit(current)) num = true;
        }
        if (!ServiceUtils.securePassword(up, low, num)) 
            throw new IllegalArgumentException("Password must have an uppercase letter, a lowercase letter and a number");
        
        // Hash the password
        String hashedPassword = ServiceUtils.hashPassword(password);
        
        // Add the customer to the database
        Customer c = customerOps.searchCustomer(email);
        if (c == null){
            int id = customerOps.createCustomer(new Customer(email, hashedPassword));
            CustomerDTO cdto = new CustomerDTO();
            cdto.setEmail(email);
            cdto.setId(id);
            return cdto;
        } else {
            throw new DuplicatedCustomerException("Customer with email " + email + " is already in the database, with ID " + c.getId());
        }
    }
    
    public static CustomerDTO logIn(String email, String password) throws CustomerNotFoundException{
        // Check input values
        if (email == null || password == null) throw new IllegalArgumentException("Parameters can't be null");
        
        // Check if the customer exists and the password is correct
        Customer c = customerOps.searchCustomer(email);
        if (c != null && c.getPassword().equals(ServiceUtils.hashPassword(password))){
            return ServiceUtils.customerTransform(c);
        } else {
            throw new CustomerNotFoundException("Customer with email " + email + " not found");
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
