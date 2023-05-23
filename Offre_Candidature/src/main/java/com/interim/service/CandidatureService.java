package com.interim.service;

import com.interim.entity.Candidature;
import com.interim.entity.Offre;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class CandidatureService {

    @Inject
    EntityManager entityManager;

    public Candidature createCandidature(Long offerId, Candidature candidature) {
        Offre offre = entityManager.find(Offre.class, offerId);
        if (offre == null) {
            throw new NotFoundException("Offre not found");
        }
        offre.setCandidatures(null);
        candidature.setOffre(offre);
        entityManager.persist(candidature);
        return candidature;
    }

    public List<Candidature> getCandidaturesByOfferId(Long offerId) {
        Offre offre = entityManager.find(Offre.class, offerId);
        if (offre == null) {
            throw new NotFoundException("Offre not found");
        }
        return offre.getCandidatures();
    }

    public Candidature getCandidatureById(Long id) {
        Candidature candidature = entityManager.find(Candidature.class, id);
        if (candidature == null) {
            throw new NotFoundException("Candidature not found");
        }
        return candidature;
    }
}