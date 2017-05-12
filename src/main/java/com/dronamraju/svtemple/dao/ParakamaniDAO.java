package com.dronamraju.svtemple.dao;


import com.dronamraju.svtemple.model.Donor;
import com.dronamraju.svtemple.util.EntityManagerUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdronamr on 05/11/17.
 */
public class ParakamaniDAO {

    private static Log log = LogFactory.getLog(ParakamaniDAO.class);
    EntityManager parakamaniEntityManager = EntityManagerUtil.getParakamaniEntityManager();

    public Donor findDonor(Long donorId){
        EntityTransaction entityTransaction = parakamaniEntityManager.getTransaction();
        try {
            log.info("findDonor..");
            return parakamaniEntityManager.find(Donor.class, donorId);
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public List getDonors() {
        EntityTransaction entityTransaction = parakamaniEntityManager.getTransaction();
        try {
            Query query = parakamaniEntityManager.createQuery("SELECT donor FROM Donor donor", Donor.class);
            List<Donor> donors = query.getResultList();
            log.info("ParakamaniDAO - Donors: " + donors);
            return donors;
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void save(Donor donor) {
        EntityTransaction entityTransaction = parakamaniEntityManager.getTransaction();
        try {
            log.info("Saving donor: " + donor);
            entityTransaction.begin();
            parakamaniEntityManager.persist(donor);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            throw new RuntimeException(e);
        }
    }

    public void updateDonor(Donor selectedDonor) {
        EntityTransaction entityTransaction = parakamaniEntityManager.getTransaction();
        try {
            parakamaniEntityManager.getTransaction().begin();
            parakamaniEntityManager.merge(selectedDonor);
            parakamaniEntityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void removeDonor(Donor selectedDonor) {
        EntityTransaction entityTransaction = parakamaniEntityManager.getTransaction();
        try {
            parakamaniEntityManager.getTransaction().begin();
            parakamaniEntityManager.remove(selectedDonor);
            parakamaniEntityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Donor find(Long id) {
        EntityTransaction entityTransaction = parakamaniEntityManager.getTransaction();
        try {
            return parakamaniEntityManager.find(Donor.class, id);
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }


}