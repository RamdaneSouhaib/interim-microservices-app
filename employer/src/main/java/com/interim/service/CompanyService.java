package com.interim.service;


import com.interim.entity.Company;
import com.interim.entity.FormData;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CompanyService {

    @ConfigProperty(name = "quarkus.http.body.uploads-directory")
    String directory;
    @Transactional
    public Company createCompany(Company company) {
        company.persist();
        System.out.println("------------------------------------------------------------------- " );
        System.out.println("company.getId() = " + company.getId());
        System.out.println("company.getUserId() = " + company.getUserId());
        System.out.println("------------------------------------------------------------------- " );
        return company;
    }

    public List<Company> getAllCompanies() {
        return Company.listAll();
    }

    public Company getCompanyById(Long id) {
        return Company.findById(id);
    }


    @Transactional
    public Company updateCompany(String id, Company company) {
        System.out.println("updateCompany id = " +id + " company desc = " + company.getDescription());
        Company entity = findByUserId(id);
        entity.setCompanyName(company.getCompanyName());
        entity.setDepartementName(company.getDepartementName());
        entity.setSubDepartementName(company.getSubDepartementName());
        entity.setSubDepartementName(company.getSubDepartementName());
        entity.setNationalIdNumber(company.getNationalIdNumber());
        entity.setEmailContact1(company.getEmailContact1());
        entity.setPhoneNumContact1(company.getPhoneNumContact1());
        entity.setEmailContact2(company.getEmailContact2());
        entity.setPhoneNumContact2(company.getPhoneNumContact2());
        entity.setAddress(company.getAddress());
        entity.setComplement(company.getComplement());
        entity.setCity(company.getCity());
        entity.setCityCode(company.getCityCode());
        entity.setCountryName(company.getCountryName());
        entity.setLinkToFacebook(company.getLinkToFacebook());
        entity.setLinkToTwitter(company.getLinkToTwitter());
        entity.setLinkToLinkedIn(company.getLinkToLinkedIn());
        entity.setDescription(company.getDescription());
        entity.persist();
        return entity;
    }

    @Transactional
    public void deleteCompany(Long id) {
        Company.deleteById(id);
    }
    @Transactional
    public Company findByUserId(String userId) {
        return Company.find("userId", userId).firstResult();
    }
    @Transactional
    public Company sendUpload(FormData data) throws IOException {
        System.out.println("sendUpload ");
        String type = data.getType();
        System.out.println("type = " + type);
        System.out.println("data.getCandidateId() = " + data.getCompanyId());
        Company company = findByUserId(data.getCompanyId());


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