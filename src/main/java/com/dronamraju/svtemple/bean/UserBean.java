package com.dronamraju.svtemple.bean;

import com.dronamraju.svtemple.model.Product;
import com.dronamraju.svtemple.model.User;
import com.dronamraju.svtemple.model.UserProduct;
import com.dronamraju.svtemple.service.ProductService;
import com.dronamraju.svtemple.service.UserService;
import com.dronamraju.svtemple.util.AES;
import com.dronamraju.svtemple.util.FacesUtil;
import com.dronamraju.svtemple.util.SendEmail;
import com.dronamraju.svtemple.util.Util;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Stream;


@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

	private static Log log = LogFactory.getLog(UserBean.class);

	@ManagedProperty("#{userService}")
	private UserService userService;

	@ManagedProperty("#{productService}")
	private ProductService productService;

	private User user = new User();

	//private Product product = new Product();

	private Date dateAndTime;

	private String additionalNotes;

	private Double totalAmount = 0.00;

	//private String[] selectedProductIds;

	private List<UserProduct> userProducts;

	private List<Product> products;

	private List<Product> selectedProducts;

	private List<Product> filteredProducts;

	private User loggedInUser;

	private List users;

	@PostConstruct
	public void init() {
		user = new User();
		selectedProducts = new ArrayList<>();
		products = productService.getProducts();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void login() {
		log.info("login()...");
		try {

			Boolean hasValidationErrors = false;

			if (user.getEmail() == null || user.getEmail().trim().length() < 1 || !Util.isValidEmail(user.getEmail())) {
				FacesUtil.getFacesContext().addMessage("email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid email is required.", null));
				hasValidationErrors = true;
			}

			if (user.getPassword() == null || user.getPassword().trim().length() < 1) {
				FacesUtil.getFacesContext().addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Password is required.", null));
				hasValidationErrors = true;
			}

			if (hasValidationErrors) {
				log.info("Validation Failed...");
				return;
			}

			loggedInUser = userService.findUser(user.getEmail(), AES.encrypt(user.getPassword()));
			FacesUtil.getRequest().getSession().setAttribute("loggedInUser", loggedInUser);
			log.info("loggedInUser: " + loggedInUser);
			if (loggedInUser == null) {
				FacesUtil.getFacesContext().addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Either invalid login or user does not exist.", null));
				return;
			}
			user = loggedInUser;
			selectedProducts = new ArrayList<>();
			FacesUtil.redirect("purchaseServices.xhtml");
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}


	public void registerUser() {
		log.info("registerUser()...");
		try {
			Boolean hasValidationErrors = false;
			log.info("User: " + user);
			if ( FacesUtil.getRequest().getSession().getAttribute("loggedInUser") != null) {
				loggedInUser = (User) FacesUtil.getRequest().getSession().getAttribute("loggedInUser");
			}

			if (user.getFirstName() == null || user.getFirstName().trim().length() < 1) {
				FacesUtil.getFacesContext().addMessage("firstName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A valid fisrt name is required.", null));
				hasValidationErrors = true;
			}

			if (user.getLastName() == null || user.getLastName().trim().length() < 1) {
				FacesUtil.getFacesContext().addMessage("lastName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A valid last name is required.", null));
				hasValidationErrors = true;
			}

			if (user.getEmail() == null || user.getEmail().trim().length() < 1 || !Util.isValidEmail(user.getEmail())) {
				FacesUtil.getFacesContext().addMessage("email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A valid email is required.", null));
				hasValidationErrors = true;
			}

			if (user.getPhoneNumber() == null || user.getPhoneNumber().trim().length() < 1 || !Util.isValidPhoneNumber(user.getPhoneNumber())) {
				FacesUtil.getFacesContext().addMessage("phoneNumber", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A valid phone number is required.", null));
				hasValidationErrors = true;
			}

			if (StringUtils.isEmpty(user.getPassword()) || !(user.getPassword().equals(user.getRePassword()))) {
				FacesUtil.getFacesContext().addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password should be at least 10 characters long and should match with re-entered.", null));
				hasValidationErrors = true;
			}

			if (user.getStreetAddress() == null || user.getStreetAddress().trim().length() < 1) {
				FacesUtil.getFacesContext().addMessage("streetAddress", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A valid street address is required.", null));
				hasValidationErrors = true;
			}

			if (user.getCity() == null || user.getPhoneNumber().trim().length() < 1) {
				FacesUtil.getFacesContext().addMessage("phoneNumber", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A valid phone number is required.", null));
				hasValidationErrors = true;
			}

			if (user.getState() == null || user.getState().trim().length() < 1) {
				FacesUtil.getFacesContext().addMessage("state", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A valid state is required.", null));
				hasValidationErrors = true;
			}

			if (user.getZip() == null || user.getZip().trim().length() < 1 || !Util.isValidZip(user.getZip())) {
				FacesUtil.getFacesContext().addMessage("zip", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A valid zip is required.", null));
				hasValidationErrors = true;
			}

			if (hasValidationErrors) {
				log.info("Validation Failed...");
				selectedProducts = new ArrayList<>();
				return;
			}
			user.setCreatedDate(Calendar.getInstance().getTime());
			user.setUpdatedDate(Calendar.getInstance().getTime());
			user.setCreatedUser(user.getFirstName() + " " + user.getLastName());
			user.setUpdatedUser(user.getFirstName() + " " + user.getLastName());
			user.setIsAdmin("N");
			user.setPassword(AES.encrypt(user.getPassword()));

			loggedInUser = userService.saveUser(user);

			StringBuilder sb = new StringBuilder();
			sb.append("<b>Thank you for registering.</b><br></br>");
			sb.append("<b>Sri Venkateswara Swamy Temple Of Colorado</b><br></br>");
			sb.append("<b>1495 S Ridge Road Castle Rock CO 80104</b><br></br>");
			sb.append("<b>Manager: 303-898-5514 | Temple: 303-660-9555 | Email: <a href='mailto:manager@svtempleco.org'>manager@svtempleco.org</a></b><br></br>");
			sb.append("<b>Website: <a href='http://www.svtempleco.org/homepage.php'>http://www.svtempleco.org</a></b><br></br>");
			sb.append("<b>Facebook: <a href='https://www.facebook.com/SVTC.COLORADO/'>SVTC.Colorado</a></b><br></br>");
			sb.append("<b>PayPal Donor: <a href='https://www.paypal.me/svtempleco'>SVTC PayPal Link</a></b><br></br>");
			String recipients = "manudr@hotmail.com";
			SendEmail.sendMail(sb.toString(), loggedInUser.getEmail(), recipients);
			FacesUtil.getRequest().getSession().setAttribute("loggedInUser", loggedInUser);
			log.info("loggedInUser: " + loggedInUser);
			FacesUtil.redirect("login.xhtml");
		} catch(Exception exception) {
			Optional<Throwable> rootCause = Stream.iterate(exception, Throwable::getCause).filter(element -> element.getCause() == null).findFirst();
			FacesUtil.getFacesContext().addMessage("selectedProducts", new FacesMessage(FacesMessage.SEVERITY_ERROR, rootCause.toString(), null));
			log.error("error occurred: ", exception);
			return;
		}
	}

	public void purchaseServices() {
		log.info("purchaseServices()...");
		try {
			Boolean hasValidationErrors = false;
			log.info("User: " + user);
			totalAmount = 0.00;

			if ( FacesUtil.getRequest().getSession().getAttribute("loggedInUser") != null) {
				loggedInUser = (User) FacesUtil.getRequest().getSession().getAttribute("loggedInUser");
			}

			log.info("loggedInUser in session: " + loggedInUser);
			log.info("selectedProducts: " + selectedProducts);

			if (selectedProducts == null || selectedProducts.size() < 1) {
				FacesUtil.getFacesContext().addMessage("selectedProducts", new FacesMessage(FacesMessage.SEVERITY_ERROR, "One or more services must be selecetd.", null));
				hasValidationErrors = true;
			}

			log.info("additionalNotes: " + additionalNotes);

			log.info("dateAndTime: " + dateAndTime);

			if (dateAndTime != null && !(Util.isValidDate(dateAndTime))) {
				log.info("date failed");
				FacesUtil.getFacesContext().addMessage("dateAndTime", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A aalid date and time is required.", null));
				hasValidationErrors = true;
			}

			if (hasValidationErrors) {
				log.info("Validation Failed...");
				selectedProducts = new ArrayList<>();
				return;
			}

			String orderNumber = Util.randomAlphaNumeric(10);

			while (userService.orderNumberExists(orderNumber)) {
				orderNumber = Util.randomAlphaNumeric(10);
			}

			log.info("orderNumber: " + orderNumber + " created at: " + Calendar.getInstance().getTime());

			FacesUtil.getRequest().getSession().setAttribute("orderNumber", orderNumber);

			for (Product selectedProd : selectedProducts) {
				UserProduct userProduct = new UserProduct();
				userProduct.setUserId(loggedInUser.getUserId());
				userProduct.setProductId(selectedProd.getProductId());
				userProduct.setOrderNumber(orderNumber);
				userProduct.setStatus("Scheduled");
				userProduct.setNotes(additionalNotes);
				userProduct.setDateAndTime(dateAndTime);
				userProduct.setUser(loggedInUser);
				userProduct.setProduct(selectedProd);
				userProduct.setCreatedDate(Calendar.getInstance().getTime());
				userProduct.setUpdatedDate(Calendar.getInstance().getTime());
				userProduct.setCreatedUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
				userProduct.setUpdatedUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
				log.info("userProduct: " + userProduct);
				productService.saveUserProduct(userProduct);
			}

			userProducts = userService.findUserProducts(orderNumber);

			StringBuilder sb = new StringBuilder();
			sb.append("<h4>Thank you. You have purchased the below temple services:</h4>");
			for (UserProduct userProduct : userProducts) {
				log.info("userProduct: " + userProduct);
				totalAmount = totalAmount + userProduct.getProduct().getPrice();
				log.info("totalAmount: " + totalAmount);
				sb.append("<b>Order Number: </b>" + userProduct.getOrderNumber() + "<br></br>");
				sb.append("<b>Service Name: </b>" + userProduct.getProduct().getName() + "<br></br>");
				sb.append("<b>Price: $</b>" + userProduct.getProduct().getPrice() + "<br></br>");
				sb.append("<b>Location: </b>" + userProduct.getProduct().getLocation() + "<br></br>");
				sb.append("<b>Schedule: </b>" + userProduct.getProduct().getSchedule() + "<br></br>");
				sb.append("<b>Date of Service: </b>" + userProduct.getProduct().getDateOfService() + "<br></br>");
				sb.append("<b>Time of Service: </b>" + userProduct.getProduct().getTimeOfService() + "<br></br>");
				sb.append("<b>Description: </b>" + userProduct.getProduct().getDescription() + "<br></br>");
				if (userProduct.getDateAndTime() != null) {
					sb.append("<b>Date and Time: </b>" + DateFormat.getDateTimeInstance(
							DateFormat.MEDIUM, DateFormat.SHORT).format(userProduct.getDateAndTime()) + "<br></br>");
				}
				if (userProduct.getNotes() != null && userProduct.getNotes().length() > 0) {
					sb.append("<b>Notes: </b>" + userProduct.getNotes() + "<br></br>");
				}
				sb.append("<br></br>");
			}
			sb.append("<b>Total Amount to be paid: </b>$" + totalAmount + "<br></br><br></br>");
			sb.append("<b>Thank you</b><br></br>");
			sb.append("<b>Sri Venkateswara Swamy Temple Of Colorado</b><br></br>");
			sb.append("<b>1495 S Ridge Road Castle Rock CO 80104</b><br></br>");
			sb.append("<b>Manager: 303-898-5514 | Temple: 303-660-9555 | Email: <a href='mailto:manager@svtempleco.org'>manager@svtempleco.org</a></b><br></br>");
			sb.append("<b>Website: <a href='http://www.svtempleco.org/homepage.php'>http://www.svtempleco.org</a></b><br></br>");
			sb.append("<b>Facebook: <a href='https://www.facebook.com/SVTC.COLORADO/'>SVTC.Colorado</a></b><br></br>");
			sb.append("<b>PayPal Donor: <a href='https://www.paypal.me/svtempleco'>SVTC PayPal Link</a></b><br></br>");
			//String recipients = userService.findValue("recipients");
			String recipients = "manudr@hotmail.com";
			SendEmail.sendMail(sb.toString(), user.getEmail(), recipients);
			loggedInUser = user;
			FacesUtil.getRequest().getSession().setAttribute("loggedInUser", loggedInUser);
			log.info("loggedInUser: " + loggedInUser);
			FacesUtil.redirect("payment.xhtml");
		} catch(Exception exception) {
			Optional<Throwable> rootCause = Stream.iterate(exception, Throwable::getCause).filter(element -> element.getCause() == null).findFirst();
			FacesUtil.getFacesContext().addMessage("selectedProducts", new FacesMessage(FacesMessage.SEVERITY_ERROR, rootCause.toString(), null));
			log.error("error occurred: ", exception);
			return;
		}
	}

	public void cancel() {
		log.info("cancel()..");
		if (loggedInUser != null) {
			FacesUtil.getRequest().getSession().removeAttribute("loggedInUser");
			FacesUtil.getRequest().getSession().invalidate();
		}
		FacesUtil.redirect("login.xhtml");
	}

	public void goToAllServicesPage() {
		log.info("goToAllServicesPage()..");
		if (loggedInUser != null && loggedInUser.getIsAdmin().equals("Y")) {
			products = productService.getProducts();
			FacesUtil.redirect("products.xhtml");
		} else {
			FacesUtil.redirect("login.xhtml");
		}
	}

	public void goToAllUsersPage() {
		log.info("goToAllUsersPage()..");
		if (loggedInUser != null && loggedInUser.getIsAdmin().equals("Y")) {
			users = userService.findAllUsers();
			FacesUtil.redirect("users.xhtml");
		} else {
			FacesUtil.redirect("login.xhtml");
		}
	}

	public void goToMyServicePage() {
		log.info("goToMyServicePage()..");
		if (loggedInUser != null) {
			userProducts = userService.findUserProducts(loggedInUser.getUserId());
			FacesUtil.redirect("userProducts.xhtml");
		} else {
			FacesUtil.redirect("login.xhtml");
		}
	}

	public void goToAllDevoteeServicesPage() {
		log.info("goToAllDevoteeServicesPage()..");
		if (loggedInUser != null) {
			userProducts = userService.findUserProducts();
			FacesUtil.redirect("allDevoteesAndServices.xhtml");
		} else {
			FacesUtil.redirect("login.xhtml");
		}
	}

	public void goToAddNewServicePage() {
		log.info("goToAddNewServicePage()..");



		if (loggedInUser != null && loggedInUser.getIsAdmin().equals("Y")) {
			FacesUtil.redirect("product.xhtml");
		} else {
			FacesUtil.redirect("login.xhtml");
		}
	}

	public void goToPurchaseServicesPage() {
		log.info("goToPurchaseServicesPage()..");
		if (loggedInUser != null) {
			FacesUtil.redirect("purchaseServices.xhtml");
		} else {
			FacesUtil.redirect("login.xhtml");
		}
	}

	public void goToUserProfilePage() {
		log.info("goToUserProfilePage()..");
		FacesUtil.redirect("userRegistration.xhtml");
	}

	public void sendPassword() {
		log.info("sendPassword()..");
		try {
			if (user.getEmail() == null || user.getEmail().trim().length() < 1 || !Util.isValidEmail(user.getEmail())) {
				FacesUtil.getFacesContext().addMessage("email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid email is required.", null));
				return;
			} else {
				String password = userService.findPassword(user.getEmail());
				if (password == null) {
					FacesUtil.getFacesContext().addMessage("email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email you provided is not found.", null));
					return;
				} else {
					String newPassword = Util.randomAlphaNumeric(6);
					userService.updateUserPassword(user.getEmail(), AES.encrypt(newPassword));
					String emailBody = "Your password is: " + newPassword + ". We recommend you to change your password.";
					SendEmail.sendMail(emailBody, user.getEmail(), null);
					log.info("Email has been sent with password.");
					FacesUtil.getFacesContext().addMessage("email", new FacesMessage(FacesMessage.SEVERITY_INFO, "Password has been sent to your email.", null));
					return;
				}
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public String updateUser() {
		log.info("updateUser()...");
		return null;
	}

	public void logout() {
		try {
			FacesUtil.getRequest().getSession().removeAttribute("loggedInUser");
			FacesUtil.getRequest().getSession().invalidate();
			FacesUtil.redirect("login.xhtml");
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserProduct> getUserProducts() {
		return userProducts;
	}

	public void setUserProducts(List<UserProduct> userProducts) {
		this.userProducts = userProducts;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<Product> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(List<Product> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	public List<Product> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(List<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public List getUsers() {
		return users;
	}

	public void setUsers(List users) {
		this.users = users;
	}
}