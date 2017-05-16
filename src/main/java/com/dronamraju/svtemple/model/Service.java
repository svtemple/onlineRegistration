package com.dronamraju.svtemple.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "SVTC_PARAKAMANI_EVENT_SERVICES", schema = "parakamani")
public class Service implements java.io.Serializable {

    private Long eventServiceId;
    private Long eventId;
    private String serviceDescription;
    private Double serviceCost;
    private Date updatedDate;
    private Date createdDate;
    private String updatedUser;
    private String createdUser;

    public Service() {

    }

    @Id
    @Column(name = "EVENT_SERVICE_ID", unique = true, nullable = false)
    public Long getEventServiceId() {
        return this.eventServiceId;
    }

    public void setEventServiceId(Long eventServiceId) {
        this.eventServiceId = eventServiceId;
    }

    @Id
    @Column(name = "EVENT_ID", unique = true, nullable = false)
    public Long getEventId() {
        return this.eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }


    @Column(name = "EVENT_SERVICE_DESCRIPTION", nullable = false, length = 10)
    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    @Column(name = "SERVICE_COST", nullable = false, length = 10)
    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    @Column(name = "UPDATE_DATE", nullable = false, length = 10)
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Column(name = "CREATE_DATE", nullable = false, length = 10)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "UPDATE_USER", nullable = false, length = 10)
    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    @Column(name = "CREATE_USER", nullable = false, length = 10)
    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    @Override
    public String toString() {
        return "Service{" +
                "eventServiceId=" + eventServiceId +
                ", eventId=" + eventId +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", serviceCost=" + serviceCost +
                ", updatedDate=" + updatedDate +
                ", createdDate=" + createdDate +
                ", updatedUser='" + updatedUser + '\'' +
                ", createdUser='" + createdUser + '\'' +
                '}';
    }
}