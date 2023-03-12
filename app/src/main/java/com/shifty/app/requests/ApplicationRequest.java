package com.shifty.app.requests;

public class ApplicationRequest {
    Long userId;
    Long jobId;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public ApplicationRequest(Long userId, Long jobId, String status) {
        this.userId = userId;
        this.jobId = jobId;
        this.status = status;
    }
}
