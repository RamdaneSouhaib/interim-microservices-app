package com.interim;

import com.interim.entity.Offre;
import com.interim.service.OffreService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/offres")

public class OffreResource {

    @Inject
    OffreService offreService;

    @POST
    @Transactional
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOffre(Offre offre) {
        System.out.println("addOffre");
        offre = offreService.create(offre);
        System.out.println("addOffre.getId = "+offre.getId());
        return Response.ok(offre).build();
    }

    @DELETE
    @RolesAllowed("admin")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteOffre(@PathParam("id") Long id) {
        offreService.delete(id);
        return Response.ok().build();
    }

    @GET
    @Path("")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findOffreAll() {
        List<Offre> offres = offreService.findAll();
        if (offres == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(offres).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findOffreById(@PathParam("id") Long id) {
        Offre offre = offreService.findById(id);
        if (offre == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(offre).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOffre(@PathParam("id") Long id, Offre offreToUpdate) {
        offreService.update(id, offreToUpdate);
        return Response.ok().build();
    }


    @GET
    @Path("filter")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Offre> filterOffres(@QueryParam("jobPosition") String jobPosition,
                                    @QueryParam("typeOfWorkplace") String typeOfWorkplace,
                                    @QueryParam("jobLocation") String jobLocation,
                                    @QueryParam("employementType") String employementType) {
        return offreService.getOffres(jobPosition, typeOfWorkplace, jobLocation, employementType);
    }




}
