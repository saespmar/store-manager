package com.saespmar.storeManager.operations;

import com.saespmar.storeManager.model.Category;
import com.saespmar.storeManager.model.Customer;
import com.saespmar.storeManager.model.Product;
import com.saespmar.storeManager.model.SubProduct;
import com.saespmar.storeManager.model.UserOrder;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ProductOpsImpl implements ProductOps {
    
    @Override
    public int createProduct(Product product) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(product);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return product.getId();
    }
    
    @Override
    public Product readProduct(int id) {
        EntityManager em = SetUpFactory.getEntityManager();
        
        String query = "SELECT p FROM Product p WHERE p.id = :prodID";
        TypedQuery<Product> tq = em.createQuery(query, Product.class);
        tq.setParameter("prodID", id);
        
        Product product = null;
        try {
            product = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return product;
    }
    
    @Override
    public void updateName(int id, String name) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            product.setName(name);
            em.persist(product);
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
    public void updateDescription(int id, String description) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            product.setDescription(description);
            em.persist(product);
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
    public void updatePrice(int id, float price) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            product.setPrice(price);
            em.persist(product);
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
    public void updateStock(int id, int stock) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            product.setStock(stock);
            em.persist(product);
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
    public void updateCategory(int id, int categoryId) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            Category category = em.find(Category.class, categoryId);
            product.setCategory(category);
            em.persist(product);
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
    public void updateSubProduct(int id, int subProductId) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            SubProduct subProduct = em.find(SubProduct.class, subProductId);
            product.setSubProduct(subProduct);
            em.persist(product);
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
    public void addToOrder(int id, int orderId, int quantity) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            UserOrder userOrder = em.find(UserOrder.class, orderId);
            product.addOrder(userOrder, quantity);
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
    public void removeFromOrder(int id, int orderId) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            UserOrder userOrder = em.find(UserOrder.class, orderId);
            product.removeOrder(userOrder);
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
    public void addToCart(int id, int customerId, int quantity) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            Customer customer = em.find(Customer.class, customerId);
            product.addToCart(customer, quantity);
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
    public void removeFromCart(int id, int customerId) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            Customer customer = em.find(Customer.class, customerId);
            product.removeFromCart(customer);
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
    public void addOpinion(int id, int customerId, int rating, String review) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            Customer customer = em.find(Customer.class, customerId);
            product.addOpinion(customer, rating, review);
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
    public void deleteProduct(int id) {
        EntityManager em = SetUpFactory.getEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            Product product = em.find(Product.class, id);
            em.remove(product);
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
