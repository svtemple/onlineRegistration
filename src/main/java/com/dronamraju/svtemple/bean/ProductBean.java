package com.dronamraju.svtemple.bean;

import com.dronamraju.svtemple.model.Product;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;

import com.dronamraju.svtemple.model.User;
import com.dronamraju.svtemple.model.UserProduct;
import com.dronamraju.svtemple.util.FacesUtil;
import com.dronamraju.svtemple.util.SendEmail;
import com.dronamraju.svtemple.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.dronamraju.svtemple.service.ProductService;

/**
 * Created by mdronamr on 2/23/17.
 */

@ManagedBean(name = "productBean")
@RequestScoped
public class ProductBean implements Serializable {

    private static Log log = LogFactory.getLog(ProductBean.class);

    public Product product;

    @ManagedProperty("#{productService}")
    private ProductService productService;

    private FacesContext facesContext = FacesContext.getCurrentInstance();

    private List<Product> products;

    private List<Product> filteredProducts;

    private List<UserProduct> filteredUserProducts;

    private Product selectedProduct;

    private List<Product> selecetdProducts;

    private List<UserProduct> userProducts;

    public List<Product> getFilteredProducts() {
        return filteredProducts;
    }

    public void setFilteredProducts(List<Product> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @PostConstruct
    public void init() {
        products = productService.getProducts();
        product = new Product(); //This is required for: Target Unreachable, 'null' returned null
    }

    public void addProduct() {
        log.info("addProduct()...");
        User loggedInUser = FacesUtil.getUserFromSession();
        Boolean hasValidationErrors = false;

        if (product.getName() == null || product.getName().trim().length() < 1) {
            FacesUtil.getFacesContext().addMessage("name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Service Name is required.", null));
            hasValidationErrors = true;
        }

        if (product.getSchedule() == null || product.getSchedule().trim().length() < 1) {
            FacesUtil.getFacesContext().addMessage("schedule", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Schedule is required.", null));
            hasValidationErrors = true;
        }

        if (product.getLocation() == null || product.getLocation().trim().length() < 1) {
            FacesUtil.getFacesContext().addMessage("location", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Location is required.", null));
            hasValidationErrors = true;
        }

        if (product.getDescription() == null || product.getDescription().trim().length() < 1) {
            FacesUtil.getFacesContext().addMessage("description", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Description is required.", null));
            hasValidationErrors = true;
        }

        if (product.getPrice() == null || product.getPrice() < 0.00 || !Util.isDouble(product.getPrice().toString())) {
            FacesUtil.getFacesContext().addMessage("price", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Price is required.", null));
            hasValidationErrors = true;
        }

        if (hasValidationErrors) {
            log.info("Validation Failed...");
            return;
        }

        //Product product = new Product(name, description, price, location, schedule, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), "Manu", "Manu");
        product.setCreatedDate(Calendar.getInstance().getTime());
        product.setUpdatedDate(Calendar.getInstance().getTime());
        product.setCreatedUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        product.setUpdatedUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        log.info("product: " + product);
        productService.saveProduct(product);
        log.info("New Temple Service has been successfully saved.");
        products = productService.getProducts();
        FacesUtil.redirect("products.xhtml");
    }

    public void updateProduct() {
        log.info("selectedProduct: " + selectedProduct);
        if (FacesUtil.getUserFromSession() == null) {
            FacesUtil.redirect("login.xhtml");
        }
        User loggedInUser = FacesUtil.getUserFromSession();
        selectedProduct.setCreatedDate(Calendar.getInstance().getTime());
        selectedProduct.setUpdatedDate(Calendar.getInstance().getTime());
        selectedProduct.setCreatedUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        selectedProduct.setUpdatedUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        productService.updateProduct(selectedProduct);
        log.info("Temple Service has been successfully updated.");
        products = productService.getProducts();
        FacesUtil.redirect("products.xhtml");
    }

    public String deleteProduct() {
        if (FacesUtil.getUserFromSession() == null) {
            FacesUtil.redirect("login.xhtml");
        }
        productService.removeProduct(selectedProduct);
        products = productService.getProducts();
        selectedProduct = null;
        FacesUtil.redirect("products.xhtml");
        return null;
    }

    public String cancel() {
        log.info("cancel()..");
        products = productService.getProducts();
        FacesUtil.redirect("products.xhtml");
        return null;
    }

    public void addNewService() {
        try {
            FacesUtil.redirect("product.xhtml");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public List<Product> getSelecetdProducts() {
        return selecetdProducts;
    }

    public void setSelecetdProducts(List<Product> selecetdProducts) {
        this.selecetdProducts = selecetdProducts;
    }

    public List<UserProduct> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(List<UserProduct> userProducts) {
        this.userProducts = userProducts;
    }

    public List<UserProduct> getFilteredUserProducts() {
        return filteredUserProducts;
    }

    public void setFilteredUserProducts(List<UserProduct> filteredUserProducts) {
        this.filteredUserProducts = filteredUserProducts;
    }
}
