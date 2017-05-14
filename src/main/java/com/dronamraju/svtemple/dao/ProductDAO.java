package com.dronamraju.svtemple.dao;


import com.dronamraju.svtemple.model.Product;
import com.dronamraju.svtemple.model.User;
import com.dronamraju.svtemple.model.UserProduct;
import com.dronamraju.svtemple.util.AES;
import com.dronamraju.svtemple.util.EntityManagerUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdronamr on 2/23/17.
 */
public class ProductDAO {

    private static Log log = LogFactory.getLog(ProductDAO.class);
    EntityManager posEntityManager = EntityManagerUtil.getPOSEntityManager();

    public Product findProduct(Long productId){
        EntityTransaction entityTransaction = posEntityManager.getTransaction();
        try {
            log.info("findProduct..");
            return posEntityManager.find(Product.class, productId);
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public List getProducts() {
        EntityTransaction entityTransaction = posEntityManager.getTransaction();
        try {
            Query query = posEntityManager.createQuery("SELECT product FROM Product product", Product.class);
            List results = query.getResultList();
            List<Product> products = query.getResultList();
//            log.info("ProductDAO - Products: " + products);
            return products;
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public List getProducts(Long userId) {
        EntityTransaction entityTransaction = posEntityManager.getTransaction();
        try {
            Query query = posEntityManager.createQuery("SELECT product FROM Product product WHERE productId = :userId", Product.class);
            query.setParameter("userId", userId);
            List<Product> products = query.getResultList();
//            log.info("ProductDAO - Products: " + products);
            return products;
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void save(Product product) {
        EntityTransaction entityTransaction = posEntityManager.getTransaction();
        try {
//            log.info("Saving product: " + product);
            entityTransaction.begin();
            posEntityManager.persist(product);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            throw new RuntimeException(e);
        }
    }

    public void save(UserProduct userProduct){
        EntityTransaction entityTransaction = posEntityManager.getTransaction();
        try {
//            log.info("Saving userProduct: " + userProduct);
            posEntityManager.getTransaction().begin();
            posEntityManager.persist(userProduct);
            posEntityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Product selectedProduct) {
        EntityTransaction entityTransaction = posEntityManager.getTransaction();
        try {
            posEntityManager.getTransaction().begin();
            posEntityManager.merge(selectedProduct);
            posEntityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void removeProduct(Product selectedProduct) {
        EntityTransaction entityTransaction = posEntityManager.getTransaction();
        try {
            posEntityManager.getTransaction().begin();
            posEntityManager.remove(selectedProduct);
            posEntityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Product find(Long id) {
        EntityTransaction entityTransaction = posEntityManager.getTransaction();
        try {
            return posEntityManager.find(Product.class, id);
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public User findUser(Long userId){
        EntityTransaction entityTransaction = posEntityManager.getTransaction();
        try {
            log.info("findUser..");
            User user = posEntityManager.find(User.class, userId);
            return user;
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public List<UserProduct> findAllUserProducts() {
        EntityTransaction entityTransaction = posEntityManager.getTransaction();
        try {
            Query query = posEntityManager.createQuery("SELECT userProduct FROM UserProduct userProduct", UserProduct.class);
            List<UserProduct> userProducts = query.getResultList();
            for (UserProduct userProduct : userProducts) {
                userProduct.setUser(findUser(userProduct.getUserId()));
                userProduct.setProduct(findProduct(userProduct.getProductId()));
            }
//            log.info("userProducts: " + userProducts.size());
            if (userProducts == null || userProducts.size() < 1) {
                return null;
            }
            return userProducts;
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}