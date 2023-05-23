package com.interim.service;


import com.interim.entity.Subscription;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;


@ApplicationScoped
public class SubscriptionService {

    @Transactional
    public Subscription createSubscription(Subscription subscription) {
        subscription.persist();
        return subscription;
    }

    public List<Subscription> getAllSubscriptions() {
        return Subscription.listAll();
    }

    public Subscription getSubscriptionById(Long id) {
        return Subscription.findById(id);
    }

    @Transactional
    public void updateSubscription(Long id, Subscription subscription) {
        Subscription.update("title = ?1, description = ?2, price = ?3, period = ?4 where id = ?5",
                subscription.getTitle(),
                subscription.getDescription(),
                subscription.getPrice(),
                subscription.getPeriod(),
                id);
    }

    @Transactional
    public void deleteSubscription(Long id) {
        Subscription.deleteById(id);
    }
}