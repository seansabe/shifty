package com.shifty.app.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
	List<Job> findByPoster(Optional<User> user);
}
