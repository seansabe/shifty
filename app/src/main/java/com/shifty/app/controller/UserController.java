package com.shifty.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shifty.app.model.User;
import com.shifty.app.model.Job;
import com.shifty.app.model.Application;
import com.shifty.app.model.UserRepository;
import com.shifty.app.model.JobRepository;
import com.shifty.app.model.ApplicationRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	JobRepository jobRepo;

	@Autowired
	ApplicationRepository appRepo;

	// GET ALL APPLICATIONS AN USER HAS SUBMITTED
	@GetMapping("/users/{userId}/applications")
	public ResponseEntity<List<Application>> getApplicationByUserId(@PathVariable("userId") Long userId) {
		try {
			Optional<User> userData = userRepo.findById(userId);
			List<Application> applications = new ArrayList<>();
			if (userData.isPresent()) {
				User user = userData.get();
				for (Application app : user.getUserApplications()) {
					applications.add(app);
				}
				return new ResponseEntity<>(applications, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET ALL POSTED JOBS FROM AN USER
	@GetMapping("/users/{userId}/jobs")
	public ResponseEntity<List<Job>> getJobsByUserId(@PathVariable("userId") Long userId) {
		try {
			Optional<User> user = userRepo.findById(userId);
			if (user.isPresent()) {
				List<Job> jobs = jobRepo.findByUser(user);
				if (!jobs.isEmpty()) {
					return new ResponseEntity<>(jobs, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET ALL USERS
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String firstName) {
		try {
			List<User> users = new ArrayList<>();
			if (firstName == null) {
				userRepo.findAll().forEach(users::add);
			} else {
				userRepo.findByFirstName(firstName).forEach(users::add);
			}
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET USER BY ID
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
		try {
			Optional<User> user = userRepo.findById(userId);
			if (user.isPresent()) {
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// CREATE USER
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User newUser = new User(user.getFirstName(), user.getLastName(), user.getAddress(), user.getPhone(),
					user.getEmail(), user.getRole(), user.getPassword());
			userRepo.save(newUser);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// DELETE ALL USERS
	@DeleteMapping("/users")
	public ResponseEntity<User> deleteAllUsers() {
		userRepo.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// DELETE USER BY ID
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable long userId) {
		try {
			Optional<User> user = userRepo.findById(userId);
			if (user.isPresent()) {
				userRepo.deleteById(userId);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}