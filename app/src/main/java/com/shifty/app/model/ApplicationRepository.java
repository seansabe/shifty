package com.shifty.app.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findById(long applicationId);

    List<Application> findByJob(Optional<Job> job);

    List<Application> findByUser(Optional<User> user);

    List<Application> findByStatus(String status);
}
