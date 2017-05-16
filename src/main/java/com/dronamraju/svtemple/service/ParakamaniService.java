package com.dronamraju.svtemple.service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.dronamraju.svtemple.dao.ParakamaniDAO;
import com.dronamraju.svtemple.model.Donor;
import com.dronamraju.svtemple.model.Event;


import java.util.*;

/**
 * Created by mdronamr on 05/11/17.
 */

@ManagedBean(name = "parakamaniService")
@ApplicationScoped
public class ParakamaniService {
    ParakamaniDAO parakamaniDAO = new ParakamaniDAO();

    public Donor findDonor(Long donorId){
        return parakamaniDAO.findDonor(donorId);
    }

    public List<Donor> getDonors() {
        List<Donor> list = parakamaniDAO.getDonors();
        return list;
    }

    public void saveDonor(Donor donor) {
        parakamaniDAO.save(donor);
    }

    public void updateDonor(Donor selectedDonor) {
        parakamaniDAO.updateDonor(selectedDonor);
    }

    public void removeDonor(Donor selectedDonor) {
        parakamaniDAO.removeDonor(selectedDonor);
    }

    public Donor find(Long id) {
        return parakamaniDAO.find(id);
    }

    public List<Event> getEvents() {
        List<Event> events = parakamaniDAO.getEvents();
        return events;
    }

}
