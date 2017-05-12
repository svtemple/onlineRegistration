package com.dronamraju.svtemple.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "PRODUCT_TABLE")
public class Product implements java.io.Serializable {

	private Long productId;
	private String name;
	private String description;
	private Double price;
	private String location;
	private String schedule;
	private Date dateOfService;
	private Date timeOfService;
	private Date updatedDate;
	private Date createdDate;
	private String updatedUser;
	private String createdUser;
	private List<UserProduct> userProducts = new ArrayList<UserProduct>(0);

	public Product() {
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "NAME", nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name="location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name="schedule")
	public String getSchedule() {
		return schedule;
	}

	@Column(name="date_of_service")
	public Date getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(Date dateOfService) {
		this.dateOfService = dateOfService;
	}

	@Column(name="time_of_service")
	public Date getTimeOfService() {
		return timeOfService;
	}

	public void setTimeOfService(Date timeOfService) {
		this.timeOfService = timeOfService;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	@Column(name="updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="updated_user")
	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	@Column(name="created_user")
	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	@Transient
	public List<UserProduct> getUserProducts() {
		return this.userProducts;
	}

	public void setUserProducts(List<UserProduct> userProducts) {
		this.userProducts = userProducts;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", location='" + location + '\'' +
				", schedule='" + schedule + '\'' +
				", dateOfService='" + dateOfService + '\'' +
				", timeOfService='" + timeOfService + '\'' +
				", updatedDate=" + updatedDate +
				", createdDate=" + createdDate +
				", updatedUser='" + updatedUser + '\'' +
				", createdUser='" + createdUser + '\'' +
				'}';
	}
}