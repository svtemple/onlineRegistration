package com.dronamraju.svtemple.service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.dronamraju.svtemple.dao.ParakamaniDAO;
import com.dronamraju.svtemple.model.Donor;


import java.util.*;

/**
 * Created by mdronamr on 05/11/17.
 */

@ManagedBean(name = "parakamaniService")
@ApplicationScoped
public class ParakamaniService {
    ParakamaniDAO donorDAO = new ParakamaniDAO();

    public Donor findDonor(Long donorId){
        return donorDAO.findDonor(donorId);
    }

    public List<Donor> getDonors() {
        List<Donor> list = donorDAO.getDonors();
        return list;
    }

    public void saveDonor(Donor donor) {
        donorDAO.save(donor);
    }

    public void updateDonor(Donor selectedDonor) {
        donorDAO.updateDonor(selectedDonor);
    }

    public void removeDonor(Donor selectedDonor) {
        donorDAO.removeDonor(selectedDonor);
    }

    public Donor find(Long id) {
        return donorDAO.find(id);
    }


}
