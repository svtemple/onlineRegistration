package com.dronamraju.svtemple.service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.dronamraju.svtemple.dao.UserDAO;
import com.dronamraju.svtemple.model.Product;
import com.dronamraju.svtemple.model.User;
import com.dronamraju.svtemple.model.UserProduct;

import java.util.List;
import java.util.Set;

/**
 * Created by mdronamr on 2/24/17.
 */

@ManagedBean(name = "userService")
@ApplicationScoped
public class UserService {
    UserDAO userDAO = new UserDAO();

    public void saveCat(Product cat) {
        userDAO.saveCat(cat);
    }

    public User saveUser(User user) {
        return userDAO.saveUser(user);
    }

    public User findUser(String email, String password) {
        return userDAO.findUser(email, password);
    }

    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    public List<UserProduct> findUserProducts(String orderNumber) {
        return userDAO.findUserProducts(orderNumber);
    }

    public List<UserProduct> findUserProducts() {
        return userDAO.findUserProducts();
    }

    public List<UserProduct> findUserProducts(Long userId) {
        return userDAO.findUserProducts(userId);
    }

    public boolean orderNumberExists(String orderNumber) {
        return userDAO.orderNumberExists(orderNumber);
    }

    public String findValue(String name) {
        return userDAO.findValue(name);
    }

    public void deleteUserProducts(String orderNumber) {
        userDAO.deleteUserProducts(orderNumber);
    }

    public String findPassword(String email) {
        return userDAO.findPassword(email);
    }

    public void updateUserPassword(String email, String newPassword) {
        userDAO.updateUserPassword(email, newPassword);
    }

    public void detachUser(User loggedInUser) {
        userDAO.detachUser(loggedInUser);
    }
}
