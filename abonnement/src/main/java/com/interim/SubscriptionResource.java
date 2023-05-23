package com.interim;

import com.interim.entity.Subscription;
import com.interim.service.SubscriptionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubscriptionResource {

    @Inject
    SubscriptionService subscriptionService;

    @POST
    public Response createSubscription(Subscription subscription) {
        Subscription createdSubscription = subscriptionService.createSubscription(subscription);
        return Response.status(Response.Status.CREATED).entity(createdSubscription).build();
    }

    @GET
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GET
    @Path("/{id}")
    public Response getSubscriptionById(@PathParam("id") Long id) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription != null) {
            return Response.ok(subscription).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateSubscription(@PathParam("id") Long id, Subscription subscription) {
        subscriptionService.updateSubscription(id, subscription);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSubscription(@PathParam("id") Long id) {
        subscriptionService.deleteSubscription(id);
        return Response.noContent().build();
    }
}