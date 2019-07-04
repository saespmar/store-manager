package com.saespmar.storeManager.exception;


public class DuplicatedCustomerException extends Exception {
    
    public DuplicatedCustomerException(String message) {
        super(message);
    }
    
    public DuplicatedCustomerException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
