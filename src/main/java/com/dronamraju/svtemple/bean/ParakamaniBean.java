package com.dronamraju.svtemple.bean;

import com.dronamraju.svtemple.model.Donor;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;

import com.dronamraju.svtemple.model.User;
import com.dronamraju.svtemple.util.FacesUtil;
import com.dronamraju.svtemple.util.SendEmail;
import com.dronamraju.svtemple.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.dronamraju.svtemple.service.ParakamaniService;

/**
 * Created by mdronamr on 2/23/17.
 */

@ManagedBean(name = "parakamaniBean")
@SessionScoped
public class ParakamaniBean implements Serializable {

    private static Log log = LogFactory.getLog(ParakamaniBean.class);

    public Donor donor;

    @ManagedProperty("#{parakamaniService}")
    private ParakamaniService parakamaniService;

    private FacesContext facesContext = FacesContext.getCurrentInstance();

    private List<Donor> donors;

    private List<Donor> filteredDonors;

    private Donor selectedDonor;

    private List<Donor> selecetdDonors;

    public List<Donor> getFilteredDonors() {
        return filteredDonors;
    }

    public void setFilteredDonors(List<Donor> filteredDonors) {
        this.filteredDonors = filteredDonors;
    }

    public Donor getSelectedDonor() {
        return selectedDonor;
    }

    public void setSelectedDonor(Donor selectedDonor) {
        this.selectedDonor = selectedDonor;
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
        if (donors == null) {
            donors = parakamaniService.getDonors();
        }
    }

    public void addDonor() {
        log.info("addDonor()...");
        User loggedInUser = (User)FacesUtil.getRequest().getSession().getAttribute("loggedInUser");
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
        if (FacesUtil.getRequest().getSession().getAttribute("loggedInUser") == null) {
            FacesUtil.redirect("login.xhtml");
        }
        User loggedInUser = (User)FacesUtil.getRequest().getSession().getAttribute("loggedInUser");
        selectedDonor.setCreateDate(Calendar.getInstance().getTime());
        selectedDonor.setUpdateDate(Calendar.getInstance().getTime());
        selectedDonor.setCreateUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        selectedDonor.setUpdateUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        parakamaniService.updateDonor(selectedDonor);
        log.info("Temple Service has been successfully updated.");
        donors = parakamaniService.getDonors();
        FacesUtil.redirect("donors.xhtml");
    }

    public String deleteDonor() {
        if (FacesUtil.getRequest().getSession().getAttribute("loggedInUser") == null) {
            FacesUtil.redirect("login.xhtml");
        }
        parakamaniService.removeDonor(selectedDonor);
        donors = parakamaniService.getDonors();
        selectedDonor = null;
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

    public List<Donor> getSelecetdDonors() {
        return selecetdDonors;
    }

    public void setSelecetdDonors(List<Donor> selecetdDonors) {
        this.selecetdDonors = selecetdDonors;
    }

}
