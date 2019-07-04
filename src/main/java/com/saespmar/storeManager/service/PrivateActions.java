package com.saespmar.storeManager.service;

import com.saespmar.storeManager.dto.*;
import com.saespmar.storeManager.exception.*;
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
    
    public static CustomerDTO changePassword(int id, String password) throws CustomerNotFoundException{
        // Check input values
        if (id < 0 || password == null) throw new IllegalArgumentException("ID must be positive and password can't be null");
        
        // Check if the password is secure
        if (password.length() < 8 || password.length() > 30) throw new IllegalArgumentException("Incorrect password length");
        boolean up = false, low = false, num = false;
        for (int i = 0; i < password.length() && !ServiceUtils.securePassword(up, low, num); i++){
            char current = password.charAt(i);
            if (!up && Character.isUpperCase(current)) up = true;
            else if (!low && Character.isLowerCase(current)) low = true;
            else if (!num && Character.isDigit(current)) num = true;
        }
        if (!ServiceUtils.securePassword(up, low, num))
            throw new IllegalArgumentException("Password must have an uppercase letter, a lowercase letter and a number");
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        
        // Update the password
        String hashedPassword = ServiceUtils.hashPassword(password);
        customerOps.updatePassword(id, hashedPassword);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeCity(int id, String city) throws CustomerNotFoundException{
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        
        // Update the city
        customerOps.updateCity(id, city);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeCountry(int id, String country) throws CustomerNotFoundException{
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        
        // Update the country
        customerOps.updateCountry(id, country);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeFirstName(int id, String firstName) throws CustomerNotFoundException{
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        
        // Update the first name
        customerOps.updateFirstName(id, firstName);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeLastName(int id, String lastName) throws CustomerNotFoundException{
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        
        // Update the last name
        customerOps.updateLastName(id, lastName);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changePhone(int id, String phone) throws CustomerNotFoundException{
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        
        // Update the phone
        customerOps.updatePhone(id, phone);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeState(int id, String state) throws CustomerNotFoundException{
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        
        // Update the state
        customerOps.updateState(id, state);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeStreet(int id, String street) throws CustomerNotFoundException{
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        
        // Update the street
        customerOps.updateStreet(id, street);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static CustomerDTO changeZipCode(int id, String zipCode) throws CustomerNotFoundException{
        // Check input values
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        
        // Check if the customer exists
        Customer c = customerOps.readCustomer(id);
        if(c == null) throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        
        // Update the zip code
        customerOps.updateZipCode(id, zipCode);
        
        // Return the customer as a way to confirm the operation has been successfully completed
        return ServiceUtils.customerTransform(c);
    }
    
    public static HashMap<ProductDTO, Integer> addToCart(int customerId, int productId, int quantity)
            throws CustomerNotFoundException, ProductNotFoundException, StockException{
        
        // Check input values
        if (customerId < 0 || productId < 0 || quantity < 1)
            throw new IllegalArgumentException("IDs must be positive and quantity must be greater than 0");
        Customer customer = customerOps.readCustomer(customerId);
        if (customer == null) throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        Product product = productOps.readProduct(productId);
        if (product == null) throw new ProductNotFoundException("Product with ID " + productId + " not found");
        if (product.getStock() < quantity) throw new StockException(quantity + " items added, but only " + product.getStock() + " available");
        
        // Check if the product was previously in the cart
        Set<ShoppingCart> cart = customer.getInCart();
        for (ShoppingCart item : cart){
            if (item.getProduct().equals(product)){
                
                // Check if there's enough stock
                int wantedAmount = item.getQuantity() + quantity;
                if (product.getStock() < wantedAmount) 
                    throw new StockException("A total of " + wantedAmount + " items added, but only " + product.getStock() + " available");
                
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
    
    public static HashMap<ProductDTO, Integer> removeFromCart(int customerId, int productId) throws CustomerNotFoundException, ProductNotFoundException{
        
        // Check input values
        if (customerId < 0 || productId < 0) throw new IllegalArgumentException("IDs must be positive");
        Customer customer = customerOps.readCustomer(customerId);
        if (customer == null) throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        Product product = productOps.readProduct(productId);
        if (product == null) throw new ProductNotFoundException("Product with ID " + productId + " not found");
        
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
        
        // The product isn't in the cart
        throw new ProductNotFoundException("The shopping cart hasn't got the product with ID " + productId);
    }
    
    public static OrderDTO placeOrder(int customerId) throws EmptyCartException, StockException, CustomerNotFoundException{
        
        // Check input values
        if (customerId < 0) throw new IllegalArgumentException("ID must be positive");
        
        // Check if the cart has products in it
        Customer customer = customerOps.readCustomer(customerId);
        if (customer == null) throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        Set<ShoppingCart> cart = customer.getInCart();
        if (cart.isEmpty()) throw new EmptyCartException("Cart is empty for customer with ID " + customerId);
        
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
                throw new StockException(
                    quantity + " items for product with ID " + p.getId() + " wanted, but only " + p.getStock() + " available"
                );
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
    
    public static OpinionDTO reviewProduct(int productId, int customerId, int rating, String review) 
            throws CustomerNotFoundException, ProductNotFoundException, OpinionException{
        
        // Check input values
        if (productId < 0 || customerId < 0 || rating < 0 || rating > 5)
            throw new IllegalArgumentException("IDs must be positive and rating must be between 0 and 5");
        Customer customer = customerOps.readCustomer(customerId);
        if (customer == null) throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        Product p = productOps.readProduct(productId);
        if (p == null) throw new ProductNotFoundException("Product with ID " + productId + " not found");
        
        // If the customer has already reviewed that product, throw an exception
        Set<Opinion> opinions = p.getOpinions();
        for (Opinion o : opinions){
            if (o.getCustomer().getId() == customerId){
                throw new OpinionException("Customer with ID " + customerId + " has already reviewed product with ID " + productId);
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
    
    public static Set<OrderDTO> getAllOrders(int customerId) throws CustomerNotFoundException{
        
        // Check input values
        if (customerId < 0) throw new IllegalArgumentException("ID must be positive");
        
        Customer c = customerOps.readCustomer(customerId);
        if (c == null) throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        Set<UserOrder> orders = c.getUserOrders();
        Set<OrderDTO> ordersDTO = new HashSet<>();
        for (UserOrder o : orders){
            ordersDTO.add(ServiceUtils.orderTransform(o));
        }
        return ordersDTO;
    }
}
