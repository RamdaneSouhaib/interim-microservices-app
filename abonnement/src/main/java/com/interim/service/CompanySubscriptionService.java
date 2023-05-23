package com.interim.service;

import com.interim.entity.CompanySubscription;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CompanySubscriptionService {

    public CompanySubscription findById(Long id) {
        return CompanySubscription.findById(id);
    }

    public List<CompanySubscription> findAll() {
        return CompanySubscription.listAll();
    }

    public void create(CompanySubscription companySubscription) {
        companySubscription.persist();
    }

    public void update(Long id, CompanySubscription companySubscription) {
        CompanySubscription entity = CompanySubscription.findById(id);
        if (entity != null) {
            entity.setSubscription(companySubscription.getSubscription());
            entity.setCompanyId(companySubscription.getCompanyId());
            entity.setBeginDate(companySubscription.getBeginDate());
            entity.setEndDate(companySubscription.getEndDate());
            entity.setStatus(companySubscription.getStatus());
        }
    }

    public void delete(Long id) {
        CompanySubscription.deleteById(id);
    }
}