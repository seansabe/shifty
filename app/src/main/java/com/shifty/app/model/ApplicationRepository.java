package com.shifty.app.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findById(long applicationId);

    List<Application> findByJob(Job job);

    List<Application> findByStatus(String status);

    List<Application> findByUserApplicationsUser(User user);

    List<Application> findByUserApplicationsUserAndStatus(User user, String status);

}
