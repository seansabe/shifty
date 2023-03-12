package com.shifty.app.requests;

import com.shifty.app.model.Job;

public class JobPostingRequest {
    private Long userId;
    private Job job;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public JobPostingRequest(Long userId, Job job) {
        this.userId = userId;
        this.job = job;
    }
}
