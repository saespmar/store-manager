package com.saespmar.storeManager.service;

import com.saespmar.storeManager.dto.*;
import com.saespmar.storeManager.model.*;
import com.saespmar.storeManager.operations.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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
    
    public static CustomerDTO registerUser(String email, String password){
        if (email == null || password == null) return null;
        if (!validMail(email)) return null;
        if (password.length() < 8 || password.length() > 30) return null;
        
        // Check if the password has an uppercase letter, a lowercase letter and a number
        boolean up = false, low = false, num = false;
        for (int i = 0; i < password.length() && !securePassword(up, low, num); i++){
            char current = password.charAt(i);
            if (!up && Character.isUpperCase(current)) up = true;
            else if (!low && Character.isLowerCase(current)) low = true;
            else if (!num && Character.isDigit(current)) num = true;
        }
        if (!securePassword(up, low, num)) return null;
        
        // Hash the password
        String hashedPassword = hashPassword(password);
        
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
        if (email == null || password == null) return null;
        Customer c = customerOps.searchCustomer(email);
        
        // Check if the password is correct
        if (c != null && c.getPassword().equals(hashPassword(password))){
            
            // Transform model into DTO
            CustomerDTO cdto = new CustomerDTO();
            cdto.setCity(c.getCity());
            cdto.setCountry(c.getCountry());
            cdto.setEmail(c.getEmail());
            cdto.setFirstName(c.getFirstName());
            cdto.setId(c.getId());
            cdto.setLastName(c.getLastName());
            cdto.setPhone(c.getPhone());
            cdto.setStreet(c.getStreet());
            cdto.setUserState(c.getUserState());
            cdto.setZipCode(c.getZipCode());
            Set<ShoppingCart> cart = c.getInCart();
            HashMap<ProductDTO, Integer> inCart = new HashMap<>();
            for (ShoppingCart item : cart){
                Product p = item.getProduct();
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
                inCart.put(pdto, item.getQuantity());
            }
            cdto.setInCart(inCart);
            
            return cdto;
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
    
    private static boolean securePassword(boolean uppercase, boolean lowercase, boolean number){
        return uppercase && lowercase && number;
    }
    
    private static String hashPassword(String password)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            
            // Convert into hex value
            BigInteger signum = new BigInteger(1, messageDigest);
            String hashedPassword = signum.toString(16);
            
            // If the hash isn't 32 bit, add zeros at the beginning
            while (hashedPassword.length() < 32)
                hashedPassword = "0" + hashedPassword;
            
            return hashedPassword;
        }
        
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
}
