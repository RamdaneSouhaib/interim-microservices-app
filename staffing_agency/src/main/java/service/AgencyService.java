package service;

import entity.FormData;
import entity.StaffingAgency;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AgencyService {

    @ConfigProperty(name = "quarkus.http.body.uploads-directory")
    String directory;

    @Transactional
    public StaffingAgency createAgency(StaffingAgency agency) {
        agency.persist();
        return agency;
    }

    public List<StaffingAgency> getAllAgencies() {
        return StaffingAgency.listAll();
    }

    public StaffingAgency getAgencyById(Long id) {

        return StaffingAgency.findById(id);
    }


    @Transactional
    public StaffingAgency updateAgency(Long id, StaffingAgency agency) {
        System.out.println("updateAgency services"  );
        StaffingAgency entity = findByUserId(id+"");
        System.out.println("entity.getId()  = " +entity.getId() );
        entity.setCompanyName(agency.getCompanyName());
        entity.setNationalIdNumber(agency.getNationalIdNumber());
        entity.setEmailContact1(agency.getEmailContact1());
        entity.setPhoneNumContact1(agency.getPhoneNumContact1());
        entity.setEmailContact2(agency.getEmailContact2());
        entity.setPhoneNumContact2(agency.getPhoneNumContact2());
        entity.setAddress(agency.getAddress());
        entity.setComplement(agency.getComplement());
        entity.setCity(agency.getCity());
        entity.setCityCode(agency.getCityCode());
        entity.setCountryName(agency.getCountryName());
        entity.setLinkToFacebook(agency.getLinkToFacebook());
        entity.setLinkToTwitter(agency.getLinkToTwitter());
        entity.setLinkToLinkedIn(agency.getLinkToLinkedIn());
        entity.setDescription(agency.getDescription());
        entity.persist();
        return entity;
    }

    @Transactional
    public void deleteAgency(String id) {
        StaffingAgency.deleteById(id);
    }
    @Transactional
    public StaffingAgency findByUserId(String userId) {
        return StaffingAgency.find("userId", userId).firstResult();
    }

    @Transactional
    public StaffingAgency sendUpload(FormData data) throws IOException {
        System.out.println("sendUpload ");
        String type = data.getType();
        System.out.println("type = " + type);
        System.out.println("data.getCandidateId() = " + data.getCompanyId());
        StaffingAgency company = findByUserId(data.getCompanyId());


        String fileName = UUID.randomUUID() + "-" + data.getFile().fileName();



        Files.copy(data.getFile().filePath(), Paths.get(directory + "/images/" + fileName));
        if(company.getImagePath() != null  ){
            Path filePath = Paths.get(directory + company.getImagePath());
            Files.delete(filePath);
        }
        company.setImagePath("images/" +fileName);
        company.persist();
        return company;
    }

}