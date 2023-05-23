package com.interim;

import com.interim.entity.CompanySubscription;
import com.interim.service.CompanySubscriptionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/company-subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanySubscriptionResource {

    @Inject
    CompanySubscriptionService companySubscriptionService;

    @GET
    public List<CompanySubscription> getAll() {
        return companySubscriptionService.findAll();
    }

    @GET
    @Path("/{id}")
    public CompanySubscription getById(@PathParam("id") Long id) {
        return companySubscriptionService.findById(id);
    }

    @POST
    public Response addCompanySubscription(CompanySubscription companySubscription) {
        companySubscriptionService.create(companySubscription);
        return Response.ok(companySubscription).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCompanySubscription(@PathParam("id") Long id, CompanySubscription companySubscription) {
        companySubscriptionService.update(id, companySubscription);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCompanySubscription(@PathParam("id") Long id) {
        companySubscriptionService.delete(id);
        return Response.noContent().build();
    }
}