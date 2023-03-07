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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    @JsonIgnore
    private Job job;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserApplication> userApplications = new HashSet<>();

    @Column(name = "status")
    private String status;

    public static String APPLIED = "Applied";
    public static String ASSIGNED = "Assigned";
    public static String CANCELED = "Canceled";

    // GETTERS, SETTERS, CONSTRUCTORS
    public long getApplicationId() {
        return applicationId;
    }

    public Job getaJob() {
        return job;
    }

    public Set<UserApplication> getUserApplications() {
        return userApplications;
    }

    public String getStatus() {
        return status;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setUserApplications(Set<UserApplication> userApplications) {
        this.userApplications = userApplications;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Application(Job job, String status) {
        this.job = job;
        this.status = status;
    }

    public Application() {
    }

    public void addUserApplication(UserApplication userApplication) {
        this.userApplications.add(userApplication);
        userApplication.setApplication(this);
    }
}
