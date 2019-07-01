package com.saespmar.storeManager.service;

import com.saespmar.storeManager.dto.*;
import com.saespmar.storeManager.model.*;
import com.saespmar.storeManager.operations.*;
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
    
}
