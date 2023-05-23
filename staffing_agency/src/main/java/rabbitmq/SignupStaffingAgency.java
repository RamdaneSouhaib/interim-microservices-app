package rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;

import entity.StaffingAgency;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import service.AgencyService;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;



@ApplicationScoped
public class SignupStaffingAgency {

    @Inject
    ObjectMapper objectMapper;

    @Inject
    AgencyService agencyService;
    @Blocking
    @Incoming("signup-agency")
    public void process(JsonObject agencyObj){

        System.out.println("firstname = "+agencyObj.getString("companyName"));


        StaffingAgency agency = new StaffingAgency();
        agency.setUserId(agencyObj.getString("id"));
        agency.setCompanyName(agencyObj.getString("companyName"));
        agency.setNationalIdNumber(agencyObj.getString("nationalIdNumber"));
        agency.setEmailContact1(agencyObj.getString("emailContact1"));
        agency.setPhoneNumContact1(agencyObj.getString("phoneNumContact1"));
        agency.setEmailContact2(agencyObj.getString("emailContact2"));
        agency.setPhoneNumContact2(agencyObj.getString("phoneNumContact2"));
        agency.setAddress(agencyObj.getString("address"));
        agency.setComplement(agencyObj.getString("complement"));
        agency.setCity(agencyObj.getString("city"));
        agency.setCityCode(agencyObj.getString("cityCode"));
        agency.setCountryName(agencyObj.getString("countryName"));
        agency.setLinkToFacebook(agencyObj.getString("linkToFacebook"));
        agency.setLinkToTwitter(agencyObj.getString("linkToTwitter"));
        agency.setLinkToLinkedIn(agencyObj.getString("linkToLinkedIn"));

        agencyService.createAgency(agency);
    }
}
