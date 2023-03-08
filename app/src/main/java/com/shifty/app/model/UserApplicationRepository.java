package com.shifty.app.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApplicationRepository extends JpaRepository<UserApplication, UserApplicationId> {

    List<UserApplication> findByUser(User user);

    List<UserApplication> findByApplication(Optional<Application> application);

    List<UserApplication> findByUserAndApplication(User user, Application application);

    Long countByUser(User user);

    Long countByApplication(Application application);

    boolean existsByUserAndApplication(User user, Application application);
}
