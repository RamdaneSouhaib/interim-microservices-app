package com.interim;

import com.interim.entity.Candidature;
import com.interim.entity.Offre;
import com.interim.service.CandidatureService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/offres/{offerId}/candidature")

public class CandidatureResource {

    @Inject
    CandidatureService candidatureService;

    @POST
    @RolesAllowed("admin")
    @Transactional
    public Response createCandidature(@PathParam("offerId") Long offerId, Candidature candidature) {
        //Offre offre = entityManager.find(Offre.class, offerId);
        Candidature createdCandidature = candidatureService.createCandidature(offerId, candidature);
        return Response.ok(createdCandidature).build();
    }

    @GET
    @RolesAllowed("admin")
    public Response getCandidaturesByOfferId(@PathParam("offerId") Long offerId) {
        List<Candidature> candidatures = candidatureService.getCandidaturesByOfferId(offerId);
        return Response.ok(candidatures).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response getCandidatureById(@PathParam("id") Long id) {
        Candidature candidature = candidatureService.getCandidatureById(id);
        return Response.ok(candidature).build();
    }
}
