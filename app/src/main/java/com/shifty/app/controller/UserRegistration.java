package com.shifty.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifty.app.model.User;
import com.shifty.app.model.UserRepository;
@CrossOrigin(origins = "http://localhost:8081") //this is for Vue.js
@RestController
@RequestMapping("/api")
public class UserRegistration {
			//1. create new user record
			@Autowired
			UserRepository userRepo;
			@PostMapping("/users")
			public ResponseEntity<User> createUser(@RequestBody User user){
				try {
					User newUser = new User(user.getFirstName(), user.getLastName(), 
										   user.getAddress(),user.getPhone(),
										   user.getEmail(), user.getRole(), user.getPassword());
					userRepo.save(newUser);
					return new ResponseEntity<>(newUser, HttpStatus.CREATED);
				} catch (Exception e) {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}}
//			//2. create new shift_poster record
//			@Autowired
//			UserRepository userRepo;
//			@PostMapping("/users/shift_poster")
//			public ResponseEntity<User> createUser(@RequestBody User user){
//				try {
//					User newUser = newUser(user.getFirstName(), user.getLastName(), 
//										   user.getAddress(),user.getPhone(),
//										   user.getEmail(),"shift_poster", user.getPassword());
//					userRepo.save(newUser);
//					return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//				} catch (Exception e) {
//					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//			}}

}
