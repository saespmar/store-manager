package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Category;
import com.saespmar.storeManager.model.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public class CategoryOpsImpl implements CategoryOps {
    
    
    @Override
    public int createCategory(Category category) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(category);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return category.getId();
    }
    
    @Override
    public Category readCategory(int id) {
        EntityManager em = SetUpFactory.getEntityManager();
        
        String query = "SELECT c FROM Category c WHERE c.id = :catID";
        TypedQuery<Category> tq = em.createQuery(query, Category.class);
        tq.setParameter("catID", id);
        
        Category category = null;
        try {
            category = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return category;
    }
    
    @Override
    public void updateName(int id, String name) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Category category = em.find(Category.class, id);
            category.setName(name);
            em.persist(category);
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
            Category category = em.find(Category.class, id);
            Product product = em.find(Product.class, productId);
            category.addProduct(product);
            em.persist(category);
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
            Category category = em.find(Category.class, id);
            Product product = em.find(Product.class, productId);
            category.removeProduct(product);
            em.persist(category);
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
    public void deleteCategory(int id) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Category category = em.find(Category.class, id);
            em.remove(category);
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
