package com.saespmar.storeManager.service;

import com.saespmar.storeManager.dto.*;
import com.saespmar.storeManager.model.*;
import com.saespmar.storeManager.operations.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Set;


public class PrivateActions {
    
    static CategoryOps categoryOps = new CategoryOpsImpl();
    static CustomerOps customerOps = new CustomerOpsImpl();
    static ProductOps productOps = new ProductOpsImpl();
    static SubProductOps subProductOps = new SubProductOpsImpl();
    static UserOrderOps userOrderOps = new UserOrderOpsImpl();
    static PublicActions publicActions = new PublicActions();
    
    public static CustomerDTO changePassword(int id, String password){
        if (id < 0 || password == null) return null;
        
        // Check if the password is secure
        if (password.length() < 8 || password.length() > 30) return null;
        boolean up = false, low = false, num = false;
        for (int i = 0; i < password.length() && !securePassword(up, low, num); i++){
            char current = password.charAt(i);
            if (!up && Character.isUpperCase(current)) up = true;
            else if (!low && Character.isLowerCase(current)) low = true;
            else if (!num && Character.isDigit(current)) num = true;
        }
        if (!securePassword(up, low, num)) return null;
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) return null;
        
        // Update the password
        String hashedPassword = hashPassword(password);
        customerOps.updatePassword(id, hashedPassword);
        
        // Return the customer as a way to confirm the operation has been successfully completed
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
