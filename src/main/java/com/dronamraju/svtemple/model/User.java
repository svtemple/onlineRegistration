package com.dronamraju.svtemple.model;

import java.util.*;

import javax.faces.bean.SessionScoped;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USER_TABLE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "FIRST_NAME"),
		@UniqueConstraint(columnNames = "LAST_NAME"),
		@UniqueConstraint(columnNames = "EMAIL"),
		@UniqueConstraint(columnNames = "PHONE_NUMBER") })
public class User implements java.io.Serializable {
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String password;
	private String rePassword;
	private String familyGothram;
	private String primaryNakshathram;
	private String primaryPadam;
	private String spouseName;
	private String spouseNakshathram;
	private String spousePadam;
	private String child1Name;
	private String child1Nakshathram;
	private String child1Padam;
	private String child2Name;
	private String child2Nakshathram;
	private String child2Padam;
	private String child3Name;
	private String child3Nakshathram;
	private String child3Padam;
	private String child4Name;
	private String child4Nakshathram;
	private String child4Padam;
	private String isAdmin;
	private Date updatedDate;
	private Date createdDate;
	private String updatedUser;
	private String createdUser;
	private List<UserProduct> userProducts = new ArrayList<>(0);

	public User() {
	}

	public User(String firstName, String lastName, String email, String phoneNumber, String streetAddress, String city, String state, String zip, String password, String familyGothram, String primaryNakshathram, String spouseName, String spouseNakshathram, String child1Name, String child1Nakshathram, String child2Name, String child2Nakshathram, String child3Name, String child3Nakshathram, String child4Name, String child4Nakshathram, String isAdmin, Date updatedDate, Date createdDate, String updatedUser, String createdUser) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.password = password;
		this.familyGothram = familyGothram;
		this.primaryNakshathram = primaryNakshathram;
		this.spouseName = spouseName;
		this.spouseNakshathram = spouseNakshathram;
		this.child1Name = child1Name;
		this.child1Nakshathram = child1Nakshathram;
		this.child2Name = child2Name;
		this.child2Nakshathram = child2Nakshathram;
		this.child3Name = child3Name;
		this.child3Nakshathram = child3Nakshathram;
		this.child4Name = child4Name;
		this.child4Nakshathram = child4Nakshathram;
		this.isAdmin = isAdmin;
		this.updatedDate = updatedDate;
		this.createdDate = createdDate;
		this.updatedUser = updatedUser;
		this.createdUser = createdUser;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name="street_address")
	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	@Column(name="city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name="zip")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	@Column(name="family_gothram")
	public String getFamilyGothram() {
		return familyGothram;
	}

	public void setFamilyGothram(String familyGothram) {
		this.familyGothram = familyGothram;
	}

	@Column(name="primary_nakshathram")
	public String getPrimaryNakshathram() {
		return primaryNakshathram;
	}

	public void setPrimaryNakshathram(String primaryNakshathram) {
		this.primaryNakshathram = primaryNakshathram;
	}

	@Column(name="primary_padam")
	public String getPrimaryPadam() {
		return primaryPadam;
	}

	public void setPrimaryPadam(String primaryPadam) {
		this.primaryPadam = primaryPadam;
	}

	@Column(name="spouse_name")
	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	@Column(name="spouse_nakshathram")
	public String getSpouseNakshathram() {
		return spouseNakshathram;
	}

	public void setSpouseNakshathram(String spouseNakshathram) {
		this.spouseNakshathram = spouseNakshathram;
	}

	@Column(name="spouse_padam")
	public String getSpousePadam() {
		return spousePadam;
	}

	public void setSpousePadam(String spousePadam) {
		this.spousePadam = spousePadam;
	}

	@Column(name="child1_name")
	public String getChild1Name() {
		return child1Name;
	}

	public void setChild1Name(String child1Name) {
		this.child1Name = child1Name;
	}

	@Column(name="child1_nakshathram")
	public String getChild1Nakshathram() {
		return child1Nakshathram;
	}

	public void setChild1Nakshathram(String child1Nakshathram) {
		this.child1Nakshathram = child1Nakshathram;
	}

	@Column(name="child1_padam")
	public String getChild1Padam() {
		return child1Padam;
	}

	public void setChild1Padam(String child1Padam) {
		this.child1Padam = child1Padam;
	}

	@Column(name="child2_name")
	public String getChild2Name() {
		return child2Name;
	}

	public void setChild2Name(String child2Name) {
		this.child2Name = child2Name;
	}

	@Column(name="child2_nakshathram")
	public String getChild2Nakshathram() {
		return child2Nakshathram;
	}

	public void setChild2Nakshathram(String child2Nakshathram) {
		this.child2Nakshathram = child2Nakshathram;
	}

	@Column(name="child2_padam")
	public String getChild2Padam() {
		return child2Padam;
	}

	public void setChild2Padam(String child2Padam) {
		this.child2Padam = child2Padam;
	}

	@Column(name="child3_name")
	public String getChild3Name() {
		return child3Name;
	}

	public void setChild3Name(String child3Name) {
		this.child3Name = child3Name;
	}

	@Column(name="child3_nakshathram")
	public String getChild3Nakshathram() {
		return child3Nakshathram;
	}

	public void setChild3Nakshathram(String child3Nakshathram) {
		this.child3Nakshathram = child3Nakshathram;
	}

	@Column(name="child3_padam")
	public String getChild3Padam() {
		return child3Padam;
	}

	public void setChild3Padam(String child3Padam) {
		this.child3Padam = child3Padam;
	}

	@Column(name="child4_name")
	public String getChild4Name() {
		return child4Name;
	}

	public void setChild4Name(String child4Name) {
		this.child4Name = child4Name;
	}

	@Column(name="child4_nakshathram")
	public String getChild4Nakshathram() {
		return child4Nakshathram;
	}

	public void setChild4Nakshathram(String child4Nakshathram) {
		this.child4Nakshathram = child4Nakshathram;
	}

	@Column(name="child4_padam")
	public String getChild4Padam() {
		return child4Padam;
	}

	public void setChild4Padam(String child4Padam) {
		this.child4Padam = child4Padam;
	}

	@Column(name="is_admin")
	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
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
		return "User{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", streetAddress='" + streetAddress + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", zip='" + zip + '\'' +
				", password='" + password + '\'' +
				", rePassword='" + rePassword + '\'' +
				", familyGothram='" + familyGothram + '\'' +
				", primaryNakshathram='" + primaryNakshathram + '\'' +
				", primaryPadam='" + primaryPadam + '\'' +
				", spouseName='" + spouseName + '\'' +
				", spouseNakshathram='" + spouseNakshathram + '\'' +
				", spousePadam='" + spousePadam + '\'' +
				", child1Name='" + child1Name + '\'' +
				", child1Nakshathram='" + child1Nakshathram + '\'' +
				", child1Padam='" + child1Padam + '\'' +
				", child2Name='" + child2Name + '\'' +
				", child2Nakshathram='" + child2Nakshathram + '\'' +
				", child2Padam='" + child2Padam + '\'' +
				", child3Name='" + child3Name + '\'' +
				", child3Nakshathram='" + child3Nakshathram + '\'' +
				", child3Padam='" + child3Padam + '\'' +
				", child4Name='" + child4Name + '\'' +
				", child4Nakshathram='" + child4Nakshathram + '\'' +
				", child4Padam='" + child4Padam + '\'' +
				", isAdmin='" + isAdmin + '\'' +
				", updatedDate=" + updatedDate +
				", createdDate=" + createdDate +
				", updatedUser='" + updatedUser + '\'' +
				", createdUser='" + createdUser + '\'' +
				", userProducts=" + userProducts +
				'}';
	}
}