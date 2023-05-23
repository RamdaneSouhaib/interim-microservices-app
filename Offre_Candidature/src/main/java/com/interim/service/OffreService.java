package com.interim.service;

import com.interim.entity.Candidature;
import com.interim.entity.Offre;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class OffreService {

    @Inject
    EntityManager entityManager;

    public List<Offre> findAll() {
        return entityManager.createQuery("SELECT o FROM Offre o", Offre.class).getResultList();
    }

    public Offre findById(Long id) {
        return entityManager.find(Offre.class, id);
    }

    public Offre create(Offre offre) {
        entityManager.persist(offre);
        return offre;
    }

    public void update(Long id, Offre offreToUpdate) {
        Offre existingOffre = entityManager.find(Offre.class, id);
        existingOffre.setJobPosition(offreToUpdate.getJobPosition());
        existingOffre.setDescription(offreToUpdate.getDescription());
        existingOffre.setTypeOfWorkplace(offreToUpdate.getTypeOfWorkplace());
        existingOffre.setJobLocation(offreToUpdate.getJobLocation());
        existingOffre.setEmployementType(offreToUpdate.getEmployementType());
        entityManager.merge(existingOffre);
    }

    public void delete(Long id) {
        Offre offre = entityManager.find(Offre.class, id);
        entityManager.remove(offre);
    }

    public List<Offre> findByCriteres(String jobPosition, String typeOfWorkplace, String jobLocation, String employementType) {
        String queryString = "SELECT o FROM Offre o WHERE o.jobPosition = :jobPosition AND o.typeOfWorkplace = :typeOfWorkplace AND o.jobLocation = :jobLocation AND o.employementType = :employementType";
        TypedQuery<Offre> query = entityManager.createQuery(queryString, Offre.class);
        query.setParameter("jobPosition", jobPosition);
        query.setParameter("typeOfWorkplace", typeOfWorkplace);
        query.setParameter("jobLocation", jobLocation);
        query.setParameter("employementType", employementType);
        return query.getResultList();
    }

    public List<Offre> getOffres(String jobPosition, String typeOfWorkplace, String jobLocation, String employementType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Offre> cq = cb.createQuery(Offre.class);
        Root<Offre> root = cq.from(Offre.class);

        List<Predicate> predicates = new ArrayList<>();

        if (jobPosition != null && !jobPosition.isEmpty()) {
            System.out.println("jobPosition");
            predicates.add(cb.like(cb.lower(root.get("jobPosition")), "%" + jobPosition.toLowerCase() + "%"));
        }

        if (typeOfWorkplace != null && !typeOfWorkplace.isEmpty()) {
            System.out.println("typeOfWorkplace");
            predicates.add(cb.like(cb.lower(root.get("typeOfWorkplace")), "%" + typeOfWorkplace.toLowerCase() + "%"));
        }

        if (jobLocation != null && !jobLocation.isEmpty()) {
            System.out.println("jobLocation");
            predicates.add(cb.like(cb.lower(root.get("jobLocation")), "%" + jobLocation.toLowerCase() + "%"));
        }

        if (employementType != null) {
            System.out.println("employementType");
            predicates.add(cb.greaterThanOrEqualTo(root.get("employementType"), employementType));
        }


        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Offre> query = entityManager.createQuery(cq);
        System.out.println("end");
        return query.getResultList();
    }

    public void addCandidature(Long offreId, Candidature candidature) {
        Offre offre = entityManager.find(Offre.class, offreId);
        //candidature.setJobOffer(offre);
        entityManager.persist(candidature);
        offre.getCandidatures().add(candidature);
        entityManager.merge(offre);
    }

    public List<Candidature> getCandidatures(Long offreId) {
        Offre offre = entityManager.find(Offre.class, offreId);
        return offre.getCandidatures();
    }

}