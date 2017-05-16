package com.dronamraju.svtemple.dao;

import com.dronamraju.svtemple.model.Event;
import com.dronamraju.svtemple.model.Product;
import com.dronamraju.svtemple.model.User;
import com.dronamraju.svtemple.model.UserProduct;
import com.dronamraju.svtemple.util.AES;
import com.dronamraju.svtemple.util.EntityManagerUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;


public class UserDAO {
	private static Log log = LogFactory.getLog(UserDAO.class);

	EntityManager posEntityManager = EntityManagerUtil.getPOSEntityManager();

	public void saveCat(Product product){
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
//			log.info("Saving product: " + product);
			entityTransaction.begin();
			posEntityManager.persist(product);
			entityTransaction.commit();
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	public void updateUserPassword(String email, String newPassword){
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			entityTransaction.begin();
			Query query = posEntityManager.createNativeQuery("UPDATE USER_TABLE SET PASSWORD = '" + newPassword + "' WHERE EMAIL = '" + email + "'");
			query.executeUpdate();
			entityTransaction.commit();
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	public User saveUser(User user){
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			log.info("Saving user...");
			User savedUser = null;
			entityTransaction.begin();
			savedUser = posEntityManager.merge(user);
			entityTransaction.commit();
//			log.info("savedUser: " + savedUser);
			return savedUser;
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	public User findUser(Long userId){
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		log.info("findUser..");
		try {
			User user = posEntityManager.find(User.class, userId);
			return user;
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	public List<User> findAllUsers(){
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			Query query = posEntityManager.createQuery("SELECT user FROM User user", User.class);
			List<User> users = query.getResultList();
			if (users == null || users.size() < 1) {
				return null;
			}
			return users;
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	public User findUser(String email, String password) {
		log.info("fing user by meail, password...");
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			Query query = posEntityManager.createQuery("SELECT user FROM User user WHERE email = :email and password = :password", User.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			List<User> users = query.getResultList();
			if (users == null || users.size() < 1) {
				return null;
			}
			User user = users.get(0);
			log.info("findUser - user: " + user.getFirstName() + ", " + user.getLastName() + ", " + user.getEmail() + ", " + user.getIsAdmin());

			return user;
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	public List<UserProduct> findUserProducts(String orderNumber) {
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			Query query = posEntityManager.createQuery("SELECT userProduct FROM UserProduct userProduct WHERE orderNumber = :orderNumber", UserProduct.class);
			query.setParameter("orderNumber", orderNumber);
			List<UserProduct> userProducts = query.getResultList();
			for (UserProduct userProduct : userProducts) {
				userProduct.setUser(findUser(userProduct.getUserId()));
				userProduct.setProduct(findProduct(userProduct.getProductId()));
			}
//			log.info("userProducts: " + userProducts.size());
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

	public List<UserProduct> findUserProducts() {
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			Query query = posEntityManager.createQuery("SELECT userProduct FROM UserProduct userProduct", UserProduct.class);
			List<UserProduct> userProducts = query.getResultList();
			for (UserProduct userProduct : userProducts) {
				userProduct.setUser(findUser(userProduct.getUserId()));
				userProduct.setProduct(findProduct(userProduct.getProductId()));
			}
//			log.info("userProducts: " + userProducts.size());
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

	public List<UserProduct> findUserProducts(Long userId) {
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			Query query = posEntityManager.createQuery("SELECT userProduct FROM UserProduct userProduct WHERE userId = :userId", UserProduct.class);
			query.setParameter("userId", userId);
			List<UserProduct> userProducts = query.getResultList();
			for (UserProduct userProduct : userProducts) {
				userProduct.setUser(findUser(userProduct.getUserId()));
				userProduct.setProduct(findProduct(userProduct.getProductId()));
			}
//			log.info("userProducts: " + userProducts.size());
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

	public String findValue(String name) {
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			log.info("findValue...");
			Query query = posEntityManager.createQuery("SELECT value FROM configuration_table configuration WHERE configuration_name = :name", String.class);
			query.setParameter("name", name);
			List<String> values = query.getResultList();
//			log.info("values: " + values);
			if (values != null && values.size() > 0) {
				return values.get(0);
			}
			return null;
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	public String findPassword(String email) {
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			log.info("findPassword by email...");
			Query query = posEntityManager.createQuery("SELECT password FROM User user WHERE email = :email", String.class);
			query.setParameter("email", email);
			List<String> values = query.getResultList();
			//log.info("values: " + values);
			if (values != null && values.size() > 0) {
				return values.get(0);
			}
			return null;
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	public void detachUser(User loggedInUser) {
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			log.info("detachUser...");
			posEntityManager.detach(loggedInUser);
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	public boolean orderNumberExists(String orderNumber) {
		log.info("orderNumberExists...");
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
//			log.info("orderNumberExists: " + orderNumber);
			Query query = posEntityManager.createQuery("SELECT orderNumber FROM UserProduct userProduct WHERE orderNumber = :orderNumber", String.class);
			query.setParameter("orderNumber", orderNumber);
			List<String> orderNumbers = query.getResultList();
//			log.info("orderNumbers: " + orderNumbers);
			if (orderNumbers == null || orderNumbers.size() < 1) {
				return false;
			}
			return true;
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}

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

	public void deleteUserProducts(String orderNumber){
		EntityTransaction entityTransaction = posEntityManager.getTransaction();
		try {
			log.info("deleteUserProducts..");
			List<UserProduct> userProducts = findUserProducts(orderNumber);
			for (UserProduct userProduct : userProducts) {
				posEntityManager.remove(userProduct);
			}
		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}
}