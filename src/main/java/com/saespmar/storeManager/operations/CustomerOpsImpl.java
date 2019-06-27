package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Customer;
import com.saespmar.storeManager.model.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public class CustomerOpsImpl implements CustomerOps {
    
    @Override
    public int createCustomer(Customer customer) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(customer);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return customer.getId();
    }
    
    @Override
    public Customer readCustomer(int id) {
        EntityManager em = SetUpFactory.getEntityManager();
        
        String query = "SELECT c FROM Customer c WHERE c.id = :custID";
        TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
        tq.setParameter("custID", id);
        
        Customer customer = null;
        try {
            customer = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return customer;
    }
    
    @Override
    public Customer searchCustomer(String email){
        EntityManager em = SetUpFactory.getEntityManager();
        
        String query = "SELECT c FROM Customer c WHERE c.email = :custEmail";
        TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
        tq.setParameter("custEmail", email);
        
        Customer customer = null;
        try {
            customer = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return customer;
    }
    
    @Override
    public void updatePassword(int id, String password) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            customer.setPassword(password);
            em.persist(customer);
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
    public void updateFirstName(int id, String firstName) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            customer.setFirstName(firstName);
            em.persist(customer);
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
    public void updateLastName(int id, String lastName) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            customer.setLastName(lastName);
            em.persist(customer);
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
    public void updatePhone(int id, String phone) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            customer.setPhone(phone);
            em.persist(customer);
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
    public void updateStreet(int id, String street) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            customer.setStreet(street);
            em.persist(customer);
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
    public void updateZipCode(int id, String zipCode) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            customer.setZipCode(zipCode);
            em.persist(customer);
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
    public void updateCity(int id, String city) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            customer.setCity(city);
            em.persist(customer);
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
    public void updateState(int id, String userState) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            customer.setUserState(userState);
            em.persist(customer);
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
    public void updateCountry(int id, String country) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            customer.setCountry(country);
            em.persist(customer);
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
    public void addToCart(int id, int productId, int quantity) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            Product product = em.find(Product.class, productId);
            customer.addToCart(product, quantity);
            em.persist(customer);
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
    public void addOpinion(int id, int productId, int rating, String review) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            Product product = em.find(Product.class, productId);
            customer.addOpinion(product, rating, review);
            em.persist(customer);
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
    public void removeOpinion(int id, int productId) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            Product product = em.find(Product.class, productId);
            customer.removeOpinion(product);
            em.persist(customer);
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
    public void deleteCustomer(int id) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Customer customer = em.find(Customer.class, id);
            em.remove(customer);
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
