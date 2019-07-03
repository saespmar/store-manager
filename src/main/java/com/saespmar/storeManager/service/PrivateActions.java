package com.saespmar.storeManager.service;

import com.saespmar.storeManager.dto.*;
import com.saespmar.storeManager.model.*;
import com.saespmar.storeManager.operations.*;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class PrivateActions {
    
    static CategoryOps categoryOps = new CategoryOpsImpl();
    static CustomerOps customerOps = new CustomerOpsImpl();
    static ProductOps productOps = new ProductOpsImpl();
    static SubProductOps subProductOps = new SubProductOpsImpl();
    static UserOrderOps userOrderOps = new UserOrderOpsImpl();
    static PublicActions publicActions = new PublicActions();
    
    public static CustomerDTO changePassword(int id, String password){
        // Check input values
        if (id < 0 || password == null) return null;
        
        // Check if the password is secure
        if (password.length() < 8 || password.length() > 30) return null;
        boolean up = false, low = false, num = false;
        for (int i = 0; i < password.length() && !ServiceUtils.securePassword(up, low, num); i++){
            char current = password.charAt(i);
            if (!up && Character.isUpperCase(current)) up = true;
            else if (!low && Character.isLowerCase(current)) low = true;
            else if (!num && Character.isDigit(current)) num = true;
        }
        if (!ServiceUtils.securePassword(up, low, num)) return null;
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) return null;
        
        // Update the password
        String hashedPassword = ServiceUtils.hashPassword(password);
        customerOps.updatePassword(id, hashedPassword);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeCity(int id, String city){
        // Check input values
        if (id < 0) return null;
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) return null;
        
        // Update the city
        customerOps.updateCity(id, city);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeCountry(int id, String country){
        // Check input values
        if (id < 0) return null;
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) return null;
        
        // Update the country
        customerOps.updateCountry(id, country);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeFirstName(int id, String firstName){
        // Check input values
        if (id < 0) return null;
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) return null;
        
        // Update the first name
        customerOps.updateFirstName(id, firstName);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeLastName(int id, String lastName){
        // Check input values
        if (id < 0) return null;
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) return null;
        
        // Update the last name
        customerOps.updateLastName(id, lastName);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changePhone(int id, String phone){
        // Check input values
        if (id < 0) return null;
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) return null;
        
        // Update the phone
        customerOps.updatePhone(id, phone);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeState(int id, String state){
        // Check input values
        if (id < 0) return null;
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) return null;
        
        // Update the state
        customerOps.updateState(id, state);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeStreet(int id, String street){
        // Check input values
        if (id < 0) return null;
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) return null;
        
        // Update the street
        customerOps.updateStreet(id, street);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeZipCode(int id, String zipCode){
        // Check input values
        if (id < 0) return null;
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) return null;
        
        // Update the zip code
        customerOps.updateZipCode(id, zipCode);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static HashMap<ProductDTO, Integer> addToCart(int customerId, int productId, int quantity){
        
        // Check input values
        if (customerId < 0 || productId < 0 || quantity < 1) return null;
        Customer customer = customerOps.readCustomer(customerId);
        if (customer == null) return null;
        Product product = productOps.readProduct(productId);
        if (product == null) return null;
        if (product.getStock() < quantity) return null;
        
        // Check if the product was previously in the cart
        Set<ShoppingCart> cart = customer.getInCart();
        for (ShoppingCart item : cart){
            if (item.getProduct().equals(product)){
                
                // Check if there's enough stock
                if (product.getStock() < item.getQuantity() + quantity) return null;
                
                // Update the amount of that product in the cart
                productOps.removeFromCart(productId, customerId);
                customerOps.addToCart(customerId, productId, item.getQuantity() + quantity);
                Customer c = customerOps.readCustomer(customerId);
                return ServiceUtils.customerTransform(c).getInCart();
            }
        }
        
        // The product wasn't in the cart before, insert it normally
        customerOps.addToCart(customerId, productId, quantity);
        Customer c = customerOps.readCustomer(customerId);
        return ServiceUtils.customerTransform(c).getInCart();
        
    }
    
    public static HashMap<ProductDTO, Integer> removeFromCart(int customerId, int productId){
        
        // Check input values
        if (customerId < 0 || productId < 0) return null;
        Customer customer = customerOps.readCustomer(customerId);
        if (customer == null) return null;
        Product product = productOps.readProduct(productId);
        if (product == null) return null;
        
        // Check if the product is in the cart
        Set<ShoppingCart> cart = customer.getInCart();
        for (ShoppingCart item : cart){
            if (item.getProduct().equals(product)){
                
                // Remove product
                productOps.removeFromCart(productId, customerId);
                
                // Return the updated cart
                Customer c = customerOps.readCustomer(customerId);
                return ServiceUtils.customerTransform(c).getInCart();
            }
        }
        
        // The product isn't in the cart, return null
        return null;
    }
    
    public static OrderDTO placeOrder(int customerId){
        
        // Check input values
        if (customerId < 0) return null;
        
        // Check if the cart has products in it
        Customer customer = customerOps.readCustomer(customerId);
        Set<ShoppingCart> cart = customer.getInCart();
        if (cart.isEmpty()) return null;
        
        // Iterate through the cart to make the order
        OrderDTO odto = new OrderDTO();
        int orderId = userOrderOps.createUserOrder(new UserOrder(new Date()));
        odto.setId(orderId);
        odto.setOrderDate(new Date());
        HashMap<ProductDTO, Integer> listOfProducts = new HashMap<>();
        userOrderOps.updateCustomer(orderId, customerId);
        for (ShoppingCart item : cart){
            Product p = item.getProduct();
            int quantity = item.getQuantity();
            
            // If there isn't enough stock, cancel the operation
            if (quantity > p.getStock()){
                userOrderOps.deleteUserOrder(orderId);
                return null;
            }
            
            // Add item to the order
            userOrderOps.addProduct(orderId, p.getId(), quantity);
            listOfProducts.put(ServiceUtils.productTransform(p), quantity);
        }
        
        // Everything went ok, empty the shopping cart and substract the amount of available stock
        for (ShoppingCart item : cart){
            int pID = item.getProduct().getId();
            productOps.removeFromCart(pID, customerId);
            int previousStock = item.getProduct().getStock();
            int bought = item.getQuantity();
            productOps.updateStock(pID, previousStock-bought);
        }
        
        odto.setProducts(listOfProducts);
        return odto;
    }
    
    public static OpinionDTO reviewProduct(int productId, int customerId, int rating, String review){
        
        // Check input values
        if (productId < 0 || customerId < 0 || rating < 0 || rating > 5) return null;
        
        // If the customer has already reviewed that product, return null
        Product p = productOps.readProduct(productId);
        Set<Opinion> opinions = p.getOpinions();
        for (Opinion o : opinions){
            if (o.getCustomer().getId() == customerId){
                return null;
            }
        }
        
        // Otherwise, create a new opinion
        productOps.addOpinion(productId, customerId, rating, review);
        OpinionDTO odto = new OpinionDTO();
        Customer c = customerOps.readCustomer(customerId);
        odto.setCustomer(c.getFirstName() + " " + c.getLastName());
        odto.setProduct(ServiceUtils.productTransform(p));
        odto.setRating(rating);
        odto.setReview(review);
        return odto;
    }
    
    public static Set<OrderDTO> getAllOrders(int customerId){
        
        // Check input values
        if (customerId < 0) return null;
        
        Customer c = customerOps.readCustomer(customerId);
        Set<UserOrder> orders = c.getUserOrders();
        Set<OrderDTO> ordersDTO = new HashSet<>();
        for (UserOrder o : orders){
            ordersDTO.add(ServiceUtils.orderTransform(o));
        }
        return ordersDTO;
    }
}
