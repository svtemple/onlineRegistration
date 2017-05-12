package com.dronamraju.svtemple.service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.dronamraju.svtemple.dao.ProductDAO;
import com.dronamraju.svtemple.model.Product;
import com.dronamraju.svtemple.model.UserProduct;

import java.util.*;

/**
 * Created by mdronamr on 2/24/17.
 */

@ManagedBean(name = "productService")
@ApplicationScoped
public class ProductService {
    ProductDAO productDAO = new ProductDAO();

    public Product findProduct(Long productId){
        return productDAO.findProduct(productId);
    }

    public List<Product> getProducts() {
        List<Product> list = productDAO.getProducts();
        return list;
    }

    public List<Product> getProducts(Long userId) {
        List<Product> list = productDAO.getProducts(userId);
        return list;
    }

    public void saveUserProduct(UserProduct userProduct){
        productDAO.save(userProduct);
    }

    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    public void updateProduct(Product selectedProduct) {
        productDAO.updateProduct(selectedProduct);
    }

    public void removeProduct(Product selectedProduct) {
        productDAO.removeProduct(selectedProduct);
    }

    public Product find(Long id) {
        return productDAO.find(id);
    }

    public List<UserProduct> findAllUserProducts() {
        return productDAO.findAllUserProducts();
    }

}
