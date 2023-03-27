package com.shifty.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifty.app.model.User;
import com.shifty.app.model.UserRepository;
import com.shifty.app.requests.UserLoginRequest;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserRepository userRepo;

    // USER LOGIN
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.getUserEmail() == null || loginRequest.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<User> userData = userRepo.findByEmail(loginRequest.getUserEmail());
            if (userData.isPresent()) {
                String password = userData.get().getPassword();
                if (password.equals(loginRequest.getPassword())) {
                    return new ResponseEntity<>(userData.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            // log the exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
