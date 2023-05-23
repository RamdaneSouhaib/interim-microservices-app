package com.interim.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Offre {

    public Offre() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String jobPosition;

    @Column(nullable = false)
    private String typeOfWorkplace;

    @Column(nullable = false)
    private String jobLocation;

    @Column(nullable = false)
    private String employementType;

    @Column(nullable = false)
    private String description;


    @OneToMany(mappedBy = "offre", cascade = CascadeType.ALL)
    @JsonManagedReference // Annotation pour la sérialisation JSON
    private List<Candidature> candidatures = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getTypeOfWorkplace() {
        return typeOfWorkplace;
    }

    public void setTypeOfWorkplace(String typeOfWorkplace) {
        this.typeOfWorkplace = typeOfWorkplace;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getEmployementType() {
        return employementType;
    }

    public void setEmployementType(String employementType) {
        this.employementType = employementType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Candidature> getCandidatures() {
        return candidatures;
    }

    public void setCandidatures(List<Candidature> candidatures) {
        this.candidatures = candidatures;
    }
}