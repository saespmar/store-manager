package com.saespmar.storeManager.service;

import com.saespmar.storeManager.dto.*;
import com.saespmar.storeManager.model.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Set;


class ServiceUtils {
    
    static boolean securePassword(boolean uppercase, boolean lowercase, boolean number){
        return uppercase && lowercase && number;
    }
    
    static String hashPassword(String password)
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
    
    static CategoryDTO categoryTransform(Category c){
        CategoryDTO cdto = new CategoryDTO();
        cdto.setId(c.getId());
        cdto.setName(c.getName());
        return cdto;
    }
    
    static CustomerDTO customerTransform(Customer c){
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
            inCart.put(productTransform(p), item.getQuantity());
        }
        cdto.setInCart(inCart);
        
        return cdto;
    }
    
    static OpinionDTO opinionTransform(Opinion o, ProductDTO pdto){
        OpinionDTO odto = new OpinionDTO();
        odto.setCustomer(o.getCustomer().getEmail());
        odto.setProduct(pdto);
        odto.setRating(o.getRating());
        if (o.getReview() != null)
            odto.setReview(o.getReview());
        return odto;
    }
    
    static OrderDTO orderTransform(UserOrder o){
        OrderDTO odto = new OrderDTO();
        odto.setDeliveryDate(o.getDeliveryDate());
        odto.setId(o.getId());
        odto.setOrderDate(o.getOrderDate());
        odto.setPayment(o.getPayment());
        odto.setShippingDate(o.getShippingDate());
        Set<OrderProducts> products = o.getProducts();
        HashMap<ProductDTO, Integer> inOrder = new HashMap<>();
        for (OrderProducts item : products){
            Product p = item.getProduct();
            inOrder.put(productTransform(p), item.getQuantity());
        }
        odto.setProducts(inOrder);
        return odto;
    }
    
    static ProductDTO productTransform(Product p){
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
    
    static SubProductDTO subproductTransform(SubProduct sp){
        SubProductDTO spdto = new SubProductDTO();
        spdto.setId(sp.getId());
        spdto.setName(sp.getName());
        return spdto;
    }
}
