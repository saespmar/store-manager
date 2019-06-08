package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Product;
import com.saespmar.storeManager.model.SubProduct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public class SubProductOpsImpl implements SubProductOps {
    
    @Override
    public int createSubProduct(SubProduct subProduct) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(subProduct);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return subProduct.getId();
    }
    
    @Override
    public SubProduct readSubProduct(int id) {
        EntityManager em = SetUpFactory.getEntityManager();
        
        String query = "SELECT s FROM SubProduct s WHERE s.id = :subID";
        TypedQuery<SubProduct> tq = em.createQuery(query, SubProduct.class);
        tq.setParameter("subID", id);
        
        SubProduct subProduct = null;
        try {
            subProduct = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return subProduct;
    }
    
    @Override
    public void updateName(int id, String name) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            SubProduct subProduct = em.find(SubProduct.class, id);
            subProduct.setName(name);
            em.persist(subProduct);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    @Override
    public void addProduct(int id, int productId) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            SubProduct subProduct = em.find(SubProduct.class, id);
            Product product = em.find(Product.class, productId);
            subProduct.addProduct(product);
            em.persist(subProduct);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    @Override
    public void removeProduct(int id, int productId) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            SubProduct subProduct = em.find(SubProduct.class, id);
            Product product = em.find(Product.class, productId);
            subProduct.removeProduct(product);
            em.persist(subProduct);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    @Override
    public void deleteSubProduct(int id) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            SubProduct subProduct = em.find(SubProduct.class, id);
            em.remove(subProduct);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }
    
}
