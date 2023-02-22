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
import com.shifty.app.model.UserRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/users")
	public ResponseEntity<User>createUser(@RequestBody User user){
		try {
			User newUser = new User(user.getFirstName(),user.getLastName(),user.getAddress(),user.getPhone(),user.getEmail(),user.getRole(),user.getPassword());
			userRepo.save(newUser);
			return new ResponseEntity< >(newUser,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/users/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long userId){
		try{
			Optional<User>userDel = userRepo.findById(userId);
			if(userDel.isPresent()) {
				userRepo.deleteById(userId);
				User newUser = new User(user.getFirstName(),user.getLastName(),user.getAddress(),user.getPhone(),user.getEmail(),user.getRole(),user.getPassword());
				userRepo.save(newUser);
				return new ResponseEntity<>(newUser, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required=false)String firstName){
		try {
			List<User> users = new ArrayList<>();
			if(firstName==null) {
				userRepo.findAll().forEach(users::add);
			}else {
				userRepo.findByFirstName(firstName).forEach(users::add);
			}
			if(users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);

		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId){
		try {
			Optional<User>user = userRepo.findById(userId);
			if(user.isPresent()) {
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/users")
	public ResponseEntity<User> deleteDB(){
		userRepo.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable long userId){
		try {
			Optional<User>user = userRepo.findById(userId);
			if(user.isPresent()) {
				userRepo.deleteById(userId);
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}