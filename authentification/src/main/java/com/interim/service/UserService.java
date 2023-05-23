package com.interim.service;

import com.interim.model.Candidate;
import com.interim.model.Company;
import com.interim.model.FormData;
import com.interim.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {
    @ConfigProperty(name = "quarkus.http.body.uploads-directory")
    String directory;



    @Inject
    EntityManager em;

    @Transactional
    public User authenticate(String email, String password) {
        PanacheQuery<User> query = User.find("email = ?1 and password = ?2", email, password);

        //TypedQuery<User> query = em.createQuery("SELECT u FROM users u WHERE u.email = :email AND u.password = :password", User.class);
        //query.setParameter("email", email);
        //query.setParameter("password", password);
        try {
            User user = query.firstResult();
            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Transactional
    public User createUser(User user) {
        em.persist(user);
        return user;
    }


    public List<User> getAllUsers() {
        return User.listAll();
    }

    public User getUserById(Long id) {
        return User.findById(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        User.deleteById(id);
    }


}
