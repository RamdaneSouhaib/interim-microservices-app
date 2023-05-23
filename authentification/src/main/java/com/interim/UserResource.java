package com.interim;


import com.interim.model.*;
import com.interim.service.JwtService;
import com.interim.service.UserService;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.resteasy.reactive.MultipartForm;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/auth")
public class UserResource {

    @Inject
    JwtService jwtService;
    @Inject
    UserService userService;


    @POST
    @Path("/login")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@RequestBody UserCredentials credentials) {
        User user = userService.authenticate(credentials.getEmail(), credentials.getPassword());
        if (user != null) {
            String token = jwtService.generateJwt(user.getRole(), user.getId());
            return Response.ok().entity(new TokenResponse(token)).build();
        } else {
            System.out.println("user = null) ");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("users")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(users).build();
    }


    @POST
    @Transactional
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(@RequestBody User user) {
        user = userService.createUser(user);

        String token = jwtService.generateJwt("admin", user.getId());
        return Response.ok(token).build();
    }


    @Channel("signup-candidate")
    Emitter<JsonObject> emitter;

    @POST
    @Path("candidate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCandidate(@RequestBody Candidate candidate) {
        User user = new User();
        user.setEmail(candidate.getEmail());
        user.setPassword(candidate.getPassword());
        user.setRole("Candidate");
        userService.createUser(user);
        candidate.setId(user.getId());
        JsonObject userObj = new JsonObject();

        userObj.put("id",user.getId());
        userObj.put("firtName",candidate.getFirstName());
        userObj.put("lastName",candidate.getLastName());
        userObj.put("email",candidate.getEmail());
        emitter.send(userObj);

        System.out.println("id user = " + user.getId());


        String token = jwtService.generateJwt("admin", user.getId());
        userObj.put("token",token);
        return Response.ok(userObj).build();
    }

    @Channel("signup-company")
    Emitter<JsonObject> companyEmitter;


    @POST
    @Transactional
    @Path("company")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCompany(@RequestBody Company company) {
        User user = new User();
        user.setEmail(company.getEmailContact1());
        user.setPassword(company.getPassword());
        user.setRole("Company");
        userService.createUser(user);
        company.setId(user.getId());

        JsonObject companyJsonObj = new JsonObject();

        companyJsonObj.put("id",user.getId());
        companyJsonObj.put("companyName",company.getCompanyName());
        companyJsonObj.put("departementName",company.getDepartementName());
        companyJsonObj.put("subDepartementName",company.getSubDepartementName());
        companyJsonObj.put("nationalIdNumber",company.getNationalIdNumber());
        companyJsonObj.put("emailContact1",company.getEmailContact2());
        companyJsonObj.put("phoneNumContact1",company.getPhoneNumContact1());
        companyJsonObj.put("emailContact2",company.getEmailContact2());
        companyJsonObj.put("phoneNumContact2",company.getPhoneNumContact2());
        companyJsonObj.put("address",company.getAddress());
        companyJsonObj.put("complement",company.getComplement());
        companyJsonObj.put("city",company.getCity());
        companyJsonObj.put("cityCode",company.getCityCode());
        companyJsonObj.put("countryName",company.getCountryName());
        companyJsonObj.put("linkToFacebook",company.getLinkToFacebook());
        companyJsonObj.put("linkToTwitter",company.getLinkToTwitter());
        companyJsonObj.put("linkToLinkedIn",company.getLinkToLinkedIn());


        String token = jwtService.generateJwt("admin", user.getId());
        companyJsonObj.put("token",token);
        companyEmitter.send(companyJsonObj);
        return Response.ok(companyJsonObj).build();
    }

    @Channel("signup-agency")
    Emitter<JsonObject> agencyEmitter;


    @POST
    @Transactional
    @Path("agency")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAgency(@RequestBody StaffingAgency agency) {
        User user = new User();
        user.setEmail(agency.getEmailContact1());
        user.setPassword(agency.getPassword());
        user.setRole("Company");
        userService.createUser(user);
        agency.setId(user.getId());

        JsonObject companyJsonObj = new JsonObject();

        companyJsonObj.put("id",user.getId());
        companyJsonObj.put("companyName",agency.getCompanyName());
        companyJsonObj.put("nationalIdNumber",agency.getNationalIdNumber());
        companyJsonObj.put("emailContact1",agency.getEmailContact2());
        companyJsonObj.put("phoneNumContact1",agency.getPhoneNumContact1());
        companyJsonObj.put("emailContact2",agency.getEmailContact2());
        companyJsonObj.put("phoneNumContact2",agency.getPhoneNumContact2());
        companyJsonObj.put("address",agency.getAddress());
        companyJsonObj.put("complement",agency.getComplement());
        companyJsonObj.put("city",agency.getCity());
        companyJsonObj.put("cityCode",agency.getCityCode());
        companyJsonObj.put("countryName",agency.getCountryName());
        companyJsonObj.put("linkToFacebook",agency.getLinkToFacebook());
        companyJsonObj.put("linkToTwitter",agency.getLinkToTwitter());
        companyJsonObj.put("linkToLinkedIn",agency.getLinkToLinkedIn());
        companyJsonObj.put("description",agency.getDescription());

        agencyEmitter.send(companyJsonObj);
        //String token = jwtService.generateJwt("Company", user.getId());
        //companyJsonObj.put("token",token);

        return Response.ok(companyJsonObj).build();
    }



}
