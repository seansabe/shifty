package com.shifty.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifty.app.model.Job;
import com.shifty.app.model.JobRepository;
import com.shifty.app.model.User;
import com.shifty.app.model.UserRepository;

@CrossOrigin(origins = "hhtp://localhost:8081")
@RestController
@RequestMapping("/api")
public class JobController {

	@Autowired
	private JobRepository jobRepo;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/users/{user_id}/jobs")
	public ResponseEntity<List<Job>> getJobsByUserId(@PathVariable("user_id") long userId){

		try {
			Optional<User> user = userRepo.findById(userId);
			if(user.isPresent()) {
				List<Job> jobs = jobRepo.findByjUser(user);
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
}
