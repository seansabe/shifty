package com.shifty.app.model;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByApplicationId(long applicationId);

    List<Application> findByJobId(long jobId);

    List<Application> findByStatus(String status);

    void deleteAll();
}
