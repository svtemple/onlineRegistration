package com.dronamraju.svtemple.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mdronamr on 5/9/17.
 */

@Entity
@Table(name = "SVTC_PARAKAMANI_DONOR", schema = "parakamani")

public class Donor implements Serializable {

    private Long donorId;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String zip4;
    private String phone;
    private String gothram;
    private String nakshathram;
    private String email;
    private String middleName;
    private String createUser;
    private Date createDate;
    private String updateUser;
    private Date updateDate;
    private String dataComplete;
    private Double pledgeAmount;
    private String pledgeFormReceived;
    private Date pledgeFormDate;
    private String foundingMember;
    private String currentActiveMember;
    private Integer eventSponsorOrder;

    @Id
    @Column(name = "DONOR_ID", unique = true, nullable = false)
    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "ADDR1")
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Column(name = "ADDR2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "ZIP")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name = "ZIP4")
    public String getZip4() {
        return zip4;
    }

    public void setZip4(String zip4) {
        this.zip4 = zip4;
    }

    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "GOTHRAM")
    public String getGothram() {
        return gothram;
    }

    public void setGothram(String gothram) {
        this.gothram = gothram;
    }

    @Column(name = "NAKSHATRAM")
    public String getNakshathram() {
        return nakshathram;
    }

    public void setNakshathram(String nakshathram) {
        this.nakshathram = nakshathram;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "MIDDLE_NAME")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "CREATE_USER")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "UPDATE_USER")
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "UPDATE_DATE")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "DATA_COMPLETE")
    public String getDataComplete() {
        return dataComplete;
    }

    public void setDataComplete(String dateComplete) {
        this.dataComplete = dateComplete;
    }

    @Column(name = "PLEDGE_AMOUNT")
    public Double getPledgeAmount() {
        return pledgeAmount;
    }

    public void setPledgeAmount(Double pledgeAmount) {
        this.pledgeAmount = pledgeAmount;
    }

    @Column(name = "PLEDGE_FORM_RECIEVED")
    public String getPledgeFormReceived() {
        return pledgeFormReceived;
    }

    public void setPledgeFormReceived(String pledgeFormReceived) {
        this.pledgeFormReceived = pledgeFormReceived;
    }

    @Column(name = "PLEDGE_FORM_DATE")
    public Date getPledgeFormDate() {
        return pledgeFormDate;
    }

    public void setPledgeFormDate(Date pledgeFormDate) {
        this.pledgeFormDate = pledgeFormDate;
    }

    @Column(name = "FOUNDING_MEMBER")
    public String getFoundingMember() {
        return foundingMember;
    }

    public void setFoundingMember(String foundingMember) {
        this.foundingMember = foundingMember;
    }

    @Column(name = "CURRENT_ACTIVE_MEMBER")
    public String getCurrentActiveMember() {
        return currentActiveMember;
    }

    public void setCurrentActiveMember(String currentActiveMember) {
        this.currentActiveMember = currentActiveMember;
    }

    @Column(name = "EVENT_SPNSR_ORDER")
    public Integer getEventSponsorOrder() {
        return eventSponsorOrder;
    }

    public void setEventSponsorOrder(Integer eventSponsorOrder) {
        this.eventSponsorOrder = eventSponsorOrder;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "donorId=" + donorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", zip4='" + zip4 + '\'' +
                ", phone='" + phone + '\'' +
                ", gothram='" + gothram + '\'' +
                ", nakshathram='" + nakshathram + '\'' +
                ", email='" + email + '\'' +
                ", middleName='" + middleName + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", dateComplete='" + dataComplete + '\'' +
                ", pledgeAmount='" + pledgeAmount + '\'' +
                ", pledgeFormReceived='" + pledgeFormReceived + '\'' +
                ", pledgeFormDate='" + pledgeFormDate + '\'' +
                ", foundingMember='" + foundingMember + '\'' +
                ", currentActiveMember='" + currentActiveMember + '\'' +
                ", eventSponsorOrder='" + eventSponsorOrder + '\'' +
                '}';
    }
}
