package com.interim;

import com.interim.service.JwtService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jwt")
public class JWTResource {

    @Inject
    JwtService jwtService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJwt() {
        String jwt = jwtService.generateJwt("admin", 12L);
        return Response.ok(jwt).build();
    }

}
