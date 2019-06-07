package com.saespmar.storeManager.operations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class SetUpFactory {
    private static final String PERSISTENCE_UNIT = "storeManager";
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    
    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }
    
    public static void closeFactory(){
        ENTITY_MANAGER_FACTORY.close();
    }
}
