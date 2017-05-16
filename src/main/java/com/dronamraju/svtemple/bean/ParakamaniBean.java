package com.dronamraju.svtemple.bean;

import com.dronamraju.svtemple.model.Donor;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;

import com.dronamraju.svtemple.model.Event;
import com.dronamraju.svtemple.model.User;
import com.dronamraju.svtemple.util.FacesUtil;
import com.dronamraju.svtemple.util.SendEmail;
import com.dronamraju.svtemple.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.dronamraju.svtemple.service.ParakamaniService;

/**
 * Created by mdronamr on 2/23/17.
 */

@ManagedBean(name = "parakamaniBean")
@RequestScoped
public class ParakamaniBean implements Serializable {

    private static Log log = LogFactory.getLog(ParakamaniBean.class);

    public Donor donor;

    public Event event;

    @ManagedProperty("#{parakamaniService}")
    private ParakamaniService parakamaniService;

    private FacesContext facesContext = FacesContext.getCurrentInstance();

    private List<Donor> donors;

    private List<Event> events;

    private List<Donor> filteredDonors;

    private List<Event> filteredEvents;

    public List<Donor> getFilteredDonors() {
        return filteredDonors;
    }

    public void setFilteredDonors(List<Donor> filteredDonors) {
        this.filteredDonors = filteredDonors;
    }

    public List<Donor> getDonors() {
        return donors;
    }

    public void setDonors(List<Donor> donors) {
        this.donors = donors;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    @PostConstruct
    public void init() {
        donor = new Donor(); //This is required for: Target Unreachable, 'null' returned null
    }

    public void addDonor() {
        log.info("addDonor()...");
        User loggedInUser = FacesUtil.getUserFromSession();
        Boolean hasValidationErrors = false;

        if (donor.getFirstName() == null || donor.getFirstName().trim().length() < 1) {
            FacesUtil.getFacesContext().addMessage("name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Service Name is required.", null));
            hasValidationErrors = true;
        }

        if (donor.getEmail() == null || donor.getEmail().trim().length() < 1) {
            FacesUtil.getFacesContext().addMessage("schedule", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Schedule is required.", null));
            hasValidationErrors = true;
        }

        if (donor.getAddress1() == null || donor.getAddress1().trim().length() < 1) {
            FacesUtil.getFacesContext().addMessage("location", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Location is required.", null));
            hasValidationErrors = true;
        }

        if (donor.getCity() == null || donor.getCity().trim().length() < 1) {
            FacesUtil.getFacesContext().addMessage("description", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Description is required.", null));
            hasValidationErrors = true;
        }

        if (donor.getPledgeAmount() == null || donor.getPledgeAmount() < 0.00 || !Util.isDouble(donor.getPledgeAmount().toString())) {
            FacesUtil.getFacesContext().addMessage("price", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Valid Pledge Amount is required.", null));
            hasValidationErrors = true;
        }

        if (hasValidationErrors) {
            log.info("Validation Failed...");
            return;
        }

        donor.setCreateDate(Calendar.getInstance().getTime());
        donor.setUpdateDate(Calendar.getInstance().getTime());
        donor.setCreateUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        donor.setUpdateUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        //log.info("donor: " + donor);
        parakamaniService.saveDonor(donor);
        log.info("New Temple Service has been successfully saved.");
        donors = parakamaniService.getDonors();
        FacesUtil.redirect("donors.xhtml");
    }

    public void updateDonor() {
        //log.info("selectedDonor: " + selectedDonor);
        if (FacesUtil.getUserFromSession() == null) {
            FacesUtil.redirect("login.xhtml");
        }
        User loggedInUser = FacesUtil.getUserFromSession();
        FacesUtil.redirect("donors.xhtml");
    }

    public String deleteDonor() {
        if (FacesUtil.getUserFromSession() == null) {
            FacesUtil.redirect("login.xhtml");
        }
        FacesUtil.redirect("donors.xhtml");
        return null;
    }

    public String cancel() {
        log.info("cancel()..");
        donors = parakamaniService.getDonors();
        FacesUtil.redirect("donors.xhtml");
        return null;
    }

    public void addNewService() {
        try {
            FacesUtil.redirect("donor.xhtml");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setParakamaniService(ParakamaniService parakamaniService) {
        this.parakamaniService = parakamaniService;
    }

    public ParakamaniService getParakamaniService() {
        return parakamaniService;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void goToAllDonorsPage() {
        log.info("goToAllDonorsPage()..");
        if (donors == null) {
            donors = parakamaniService.getDonors();
        }
        FacesUtil.redirect("donors.xhtml");
    }

    public void goToAllEventsPage() {
        log.info("goToAllEventsPage()..");
        if (events == null) {
            events = parakamaniService.getEvents();
        }
        FacesUtil.redirect("events.xhtml");
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> getFilteredEvents() {
        return filteredEvents;
    }

    public void setFilteredEvents(List<Event> filteredEvents) {
        this.filteredEvents = filteredEvents;
    }
}
