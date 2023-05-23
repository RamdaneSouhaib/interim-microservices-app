package com.interim.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interim.entity.Company;
import com.interim.service.CompanyService;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RabbitMqTest {
    @Inject
    ObjectMapper objectMapper;

    @Inject
    CompanyService companyService;
    @Blocking
    @Incoming("signup-company")
    public void process(JsonObject companyObj){

        System.out.println("firstname = "+companyObj.getString("firstname"));
        System.out.println("lastname = "+companyObj.getString("lastname"));


        Company company = new Company();
        company.setUserId(companyObj.getString("id"));
        company.setCompanyName(companyObj.getString("companyName"));
        company.setDepartementName(companyObj.getString("departementName"));
        company.setSubDepartementName(companyObj.getString("subDepartementName"));
        company.setNationalIdNumber(companyObj.getString("nationalIdNumber"));
        company.setEmailContact1(companyObj.getString("emailContact1"));
        company.setPhoneNumContact1(companyObj.getString("phoneNumContact1"));
        company.setEmailContact2(companyObj.getString("emailContact2"));
        company.setPhoneNumContact2(companyObj.getString("phoneNumContact2"));
        company.setAddress(companyObj.getString("address"));
        company.setComplement(companyObj.getString("complement"));
        company.setCity(companyObj.getString("city"));
        company.setCityCode(companyObj.getString("cityCode"));
        company.setCountryName(companyObj.getString("countryName"));
        company.setLinkToFacebook(companyObj.getString("linkToFacebook"));
        company.setLinkToTwitter(companyObj.getString("linkToTwitter"));
        company.setLinkToLinkedIn(companyObj.getString("linkToLinkedIn"));

        companyService.createCompany(company);
    }
}
