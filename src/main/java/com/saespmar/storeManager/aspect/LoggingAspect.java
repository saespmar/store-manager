package com.saespmar.storeManager.aspect;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;


public class LoggingAspect {
    
    public Object logging(ProceedingJoinPoint pjp) {
        String start = pjp.getSignature().toShortString() + " starts";
        Logger.getLogger(pjp.getSignature().toString()).log(Level.INFO, start);
        long t1 = System.currentTimeMillis();
        Object returnedValue = null;
        try {
            returnedValue = pjp.proceed(pjp.getArgs());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis() - t1;
        String end = pjp.getSignature().toShortString() + " ends in " + t2 + " ms";
        Logger.getLogger(pjp.getSignature().toString()).log(Level.INFO, end);
        return returnedValue;
    }
}
