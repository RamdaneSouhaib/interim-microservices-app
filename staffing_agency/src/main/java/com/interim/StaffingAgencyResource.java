package com.interim;


import entity.FormData;
import entity.StaffingAgency;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.MultipartForm;
import service.AgencyService;

import java.io.IOException;
import java.util.List;

@Path("/staffingagency")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffingAgencyResource {

    @Inject
    AgencyService agencyService;


    @GET
    @RolesAllowed("admin")
    public Response getAllAgencies() {
        List<StaffingAgency> companies = agencyService.getAllAgencies();
        return Response.ok(companies).build();
    }

    @GET
    @RolesAllowed("admin")
    @Path("/{id}")
    public Response getAgencyById(@PathParam("id") Long id) {
        StaffingAgency agency =agencyService.getAgencyById(id);
        if (agency != null) {
            return Response.ok(agency).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @RolesAllowed("admin")
    public Response createAgency(StaffingAgency agency) {
        StaffingAgency newAgency = agencyService.createAgency(agency);
        return Response.ok(newAgency).build();
    }

    @PUT
    @RolesAllowed("admin")
    @Path("/{id}")
    public Response updateAgency(@PathParam("id") Long id, StaffingAgency agency) {
        System.out.println("updateAgency"  );
        System.out.println("id  = " +id );
        StaffingAgency updatedAgency = agencyService.updateAgency(id, agency);
        return Response.ok(updatedAgency).build();
    }

    @DELETE
    @RolesAllowed("admin")
    @Path("/{id}")
    public Response deleteAgency(@PathParam("id") String id) {
        agencyService.deleteAgency(id);
        return Response.ok().build();
    }

    @Path("upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @POST
    public Response sendUpload(@MultipartForm FormData data) {
        System.out.println("sendUpload ");
        StaffingAgency agency = null;
        try {
            agency = agencyService.sendUpload(data);
        } catch (IOException e) {
            System.out.println("IOException exception ..,,mm ");
            throw new RuntimeException(e);
        }
        System.out.println("sendUpload id = " + agency.getId());
        return Response.ok(agency).status(201).build();

    }
}