package com.shifty.app.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByFirstName(String firstName);

	List<User> findByLastName(String lastName);

	Optional<User> findById(long userId);

	Optional<User> findByEmail(String email);

	List<User> findByRole(String role);

	void deleteAll();
}
