package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Customer;
import com.saespmar.storeManager.model.Product;
import com.saespmar.storeManager.model.UserOrder;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public class UserOrderOpsImpl implements UserOrderOps {
    
    @Override
    public int createUserOrder(UserOrder userOrder) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(userOrder);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return userOrder.getId();
    }
    
    @Override
    public UserOrder readUserOrder(int id) {
        EntityManager em = SetUpFactory.getEntityManager();
        
        String query = "SELECT uo FROM UserOrder uo WHERE uo.id = :orderID";
        TypedQuery<UserOrder> tq = em.createQuery(query, UserOrder.class);
        tq.setParameter("orderID", id);
        
        UserOrder userOrder = null;
        try {
            userOrder = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return userOrder;
    }
    
    @Override
    public void updateOrderDate(int id, Date orderDate) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            UserOrder userOrder = em.find(UserOrder.class, id);
            userOrder.setOrderDate(orderDate);
            em.persist(userOrder);
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
    public void updateShippingDate(int id, Date shippingDate) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            UserOrder userOrder = em.find(UserOrder.class, id);
            userOrder.setShippingDate(shippingDate);
            em.persist(userOrder);
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
    public void updateDeliveryDate(int id, Date deliveryDate) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            UserOrder userOrder = em.find(UserOrder.class, id);
            userOrder.setDeliveryDate(deliveryDate);
            em.persist(userOrder);
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
    public void updatePayment(int id, String payment) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            UserOrder userOrder = em.find(UserOrder.class, id);
            userOrder.setPayment(payment);
            em.persist(userOrder);
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
    public void updateCustomer(int id, int customerId) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            UserOrder userOrder = em.find(UserOrder.class, id);
            Customer customer = em.find(Customer.class, customerId);
            userOrder.setCustomer(customer);
            em.persist(userOrder);
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
    public void addProduct(int id, int productId, int quantity) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            UserOrder userOrder = em.find(UserOrder.class, id);
            Product product = em.find(Product.class, productId);
            userOrder.addProduct(product, quantity);
            em.persist(userOrder);
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
    public void deleteUserOrder(int id) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            UserOrder userOrder = em.find(UserOrder.class, id);
            em.remove(userOrder);
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
