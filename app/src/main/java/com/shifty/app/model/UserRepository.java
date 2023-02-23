package com.shifty.app.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByFirstName(String firstName);

	List<User> findByLastName(String lastName);

	List<User> findByAddress(String address);

	List<User> findByPhone(String phone);

	List<User> findByEmail(String email);

	List<User> findByRole(String role);

	List<User> findByPassword(String password);

	Optional<User> findByUserId(String userId);

	void deleteAll();
}
