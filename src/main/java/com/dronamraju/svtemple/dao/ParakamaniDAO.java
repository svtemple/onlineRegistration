package com.dronamraju.svtemple.dao;


import com.dronamraju.svtemple.model.Donor;
import com.dronamraju.svtemple.model.Event;
import com.dronamraju.svtemple.util.EntityManagerUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdronamr on 05/11/17.
 */
public class ParakamaniDAO {

    private static Log log = LogFactory.getLog(ParakamaniDAO.class);
    private static EntityManagerFactory parakamaniEntityManagerFactory;
    private static EntityManager parakamaniEntityManager;

    public Donor findDonor(Long donorId){
        parakamaniEntityManagerFactory = Persistence.createEntityManagerFactory("parakamani-jpa");
        parakamaniEntityManager = parakamaniEntityManagerFactory.createEntityManager();
        try {
            log.info("findDonor..");
            return parakamaniEntityManager.find(Donor.class, donorId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            parakamaniEntityManager.close();
            parakamaniEntityManagerFactory.close();
        }
    }

    public List<Donor> getDonors() {
        parakamaniEntityManagerFactory = Persistence.createEntityManagerFactory("parakamani-jpa");
        parakamaniEntityManager = parakamaniEntityManagerFactory.createEntityManager();
        try {
            Query query = parakamaniEntityManager.createQuery("SELECT donor FROM Donor donor", Donor.class);
            List<Donor> donors = query.getResultList();
//            log.info("ParakamaniDAO - Donors: " + donors);
            return donors;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            parakamaniEntityManager.close();
            parakamaniEntityManagerFactory.close();
        }
    }

    public List<Event> getEvents() {
        parakamaniEntityManagerFactory = Persistence.createEntityManagerFactory("parakamani-jpa");
        parakamaniEntityManager = parakamaniEntityManagerFactory.createEntityManager();
        try {
            Query query = parakamaniEntityManager.createQuery("SELECT event FROM Event event", Event.class);
            List<Event> events = query.getResultList();
            log.info("ParakamaniDAO - events: " + events);
            return events;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            parakamaniEntityManager.close();
            parakamaniEntityManagerFactory.close();
        }
    }

    public void save(Donor donor) {
        parakamaniEntityManagerFactory = Persistence.createEntityManagerFactory("parakamani-jpa");
        parakamaniEntityManager = parakamaniEntityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = parakamaniEntityManager.getTransaction();
        try {
//            log.info("Saving donor: " + donor);
            entityTransaction.begin();
            parakamaniEntityManager.persist(donor);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            throw new RuntimeException(e);
        } finally {
            parakamaniEntityManager.close();
            parakamaniEntityManagerFactory.close();
        }
    }

    public void updateDonor(Donor selectedDonor) {
        parakamaniEntityManagerFactory = Persistence.createEntityManagerFactory("parakamani-jpa");
        parakamaniEntityManager = parakamaniEntityManagerFactory.createEntityManager();
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
        } finally {
            parakamaniEntityManager.close();
            parakamaniEntityManagerFactory.close();
        }
    }

    public void removeDonor(Donor selectedDonor) {
        parakamaniEntityManagerFactory = Persistence.createEntityManagerFactory("parakamani-jpa");
        parakamaniEntityManager = parakamaniEntityManagerFactory.createEntityManager();
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
        } finally {
            parakamaniEntityManager.close();
            parakamaniEntityManagerFactory.close();
        }
    }

    public Donor find(Long id) {
        parakamaniEntityManagerFactory = Persistence.createEntityManagerFactory("parakamani-jpa");
        parakamaniEntityManager = parakamaniEntityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = parakamaniEntityManager.getTransaction();
        try {
            return parakamaniEntityManager.find(Donor.class, id);
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            parakamaniEntityManager.close();
            parakamaniEntityManagerFactory.close();
        }
    }

}