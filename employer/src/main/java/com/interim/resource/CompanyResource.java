package com.interim.resource;


import com.interim.entity.Company;
import com.interim.entity.FormData;
import com.interim.service.CompanyService;
import org.jboss.resteasy.reactive.MultipartForm;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.List;

@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyResource {

    @Inject
    CompanyService companyService;

    @Context
    UriInfo uriInfo;

    @Context
    HttpHeaders httpHeaders;

    @Context
    Request request;

    @RolesAllowed("admin")
    @GET
    public Response getAllCompanies() {
        // Access request URI and print it
        String uri = uriInfo.getRequestUri().toString();
        System.out.println("Request URI: " + uri);

        // Access request headers and print them
        MultivaluedMap<String, String> headers = httpHeaders.getRequestHeaders();
        for (String header : headers.keySet()) {
            System.out.println("Header: " + header + " = " + headers.getFirst(header));
        }

        // Access request method and print it
        String method = request.getMethod();
        System.out.println("Request Method: " + method);

        // Access request body and print it
       // System.out.println("Request Body: " + requestBody);


        List<Company> companies = companyService.getAllCompanies();
        return Response.ok(companies).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response getCompanyById(@PathParam("id") Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return Response.ok(company).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createCompany(Company company) {
        Company newCompany = companyService.createCompany(company);
        return Response.ok(newCompany).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCompany(@PathParam("id") String id, Company company) {
        System.out.println("updateCompany ");
        Company updatedCompany = companyService.updateCompany(id, company);
        return Response.ok(updatedCompany).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCompany(@PathParam("id") Long id) {
        companyService.deleteCompany(id);
        return Response.ok().build();
    }

    @Path("upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @POST
    public Response sendUpload(@MultipartForm FormData data) {
        System.out.println("sendUpload ");
        Company company = null;
        try {
            company = companyService.sendUpload(data);
        } catch (IOException e) {
            System.out.println("IOException exception ..,,mm ");
            throw new RuntimeException(e);
        }
        System.out.println("sendUpload id = " + company.getId());
        return Response.ok(company).status(201).build();

    }


}