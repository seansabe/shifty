package com.shifty.app.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    @JsonIgnore
    private Job aJob;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserApplication> userApplications = new HashSet<>();

    @Column(name = "status")
    private String status;

    // GETTERS, SETTERS, CONSTRUCTORS
    public Long getApplicationId() {
        return applicationId;
    }

    public Job getaJob() {
        return aJob;
    }

    public Set<UserApplication> getUserApplications() {
        return userApplications;
    }

    public String getStatus() {
        return status;
    }

    public void setJob(Job aJob) {
        this.aJob = aJob;
    }

    public void setUserApplications(Set<UserApplication> userApplications) {
        this.userApplications = userApplications;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Application(Job aJob, Set<UserApplication> userApplications, String status) {
        this.aJob = aJob;
        this.userApplications = userApplications;
        this.status = status;
    }

    public Application() {
    }

    public void addUserApplication(UserApplication userApplication) {
        this.userApplications.add(userApplication);
        userApplication.setApplication(this);
    }
}
