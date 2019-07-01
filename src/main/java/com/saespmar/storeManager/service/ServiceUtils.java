package com.saespmar.storeManager.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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
}
