package com.shifty.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long applicationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    @JsonIgnore
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "status")
    private String status;

    // GETTERS, SETTERS, CONSTRUCTORS
    public long getApplicationId() {
        return applicationId;
    }

    public Job getJob() {
        return job;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Application(Job job, User user, String status) {
        this.job = job;
        this.user = user;
        this.status = status;
        // job.getJobApplications().add(this);
        // user.getUserApplications().add(this);
    }

    public Application() {
    }
}
