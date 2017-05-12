package com.dronamraju.svtemple.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "SVTC_PARAKAMANI_EVENT", schema = "svt07302009")
public class Event implements java.io.Serializable {

	private Long eventId;
	private String eventDescription;
	private Date eventDate;
	private String venue;
	private Date updatedDate;
	private Date createdDate;
	private String updatedUser;
	private String createdUser;
	private Integer deductFromEvent;
	private String incomeCategory;
	private String incomeSubCategory;

	public Event() {

	}


	@Id
	@Column(name = "EVENT_ID", unique = true, nullable = false)
	public Long getEventId() {
		return this.eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	@Column(name = "EVENT_DESCR", nullable = false, length = 10)
	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	@Column(name = "EVENT_DATE", nullable = false, length = 10)
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	@Column(name = "VENUE", nullable = false, length = 10)
	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
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

	@Column(name = "DEDUCT_FROM_EVENT", nullable = false, length = 10)
	public Integer getDeductFromEvent() {
		return deductFromEvent;
	}

	public void setDeductFromEvent(Integer deductFromEvent) {
		this.deductFromEvent = deductFromEvent;
	}

	@Column(name = "INCOME_CATEGORY", nullable = false, length = 10)
	public String getIncomeCategory() {
		return incomeCategory;
	}

	public void setIncomeCategory(String incomeCategory) {
		this.incomeCategory = incomeCategory;
	}

	@Column(name = "INCOME_SUB_CATEGORY", nullable = false, length = 10)
	public String getIncomeSubCategory() {
		return incomeSubCategory;
	}

	public void setIncomeSubCategory(String incomeSubCategory) {
		this.incomeSubCategory = incomeSubCategory;
	}

	@Override
	public String toString() {
		return "Event{" +
				"eventId=" + eventId +
				", eventDescription='" + eventDescription + '\'' +
				", eventDate=" + eventDate +
				", venue='" + venue + '\'' +
				", updatedDate=" + updatedDate +
				", createdDate=" + createdDate +
				", updatedUser='" + updatedUser + '\'' +
				", createdUser='" + createdUser + '\'' +
				", deductFromEvent=" + deductFromEvent +
				", incomeCategory='" + incomeCategory + '\'' +
				", incomeSubCategory='" + incomeSubCategory + '\'' +
				'}';
	}
}