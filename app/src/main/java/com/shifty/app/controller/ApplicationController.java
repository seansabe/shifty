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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifty.app.model.Application;
import com.shifty.app.model.ApplicationRepository;
import com.shifty.app.model.User;
import com.shifty.app.model.UserApplication;
import com.shifty.app.model.UserApplicationRepository;
import com.shifty.app.model.UserRepository;

@CrossOrigin(origins = "hhtp://localhost:8081")
@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationRepository appRepo;
    
    @Autowired
    private UserApplicationRepository userAppRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @GetMapping("/userapplications/users/{applicationId}")
    public ResponseEntity<List<User>> getUsersByApplicationId(@PathVariable("applicationId") Long applicationId){
        try {
            Optional<Application> application = appRepo.findById(applicationId);
            if(application.isPresent()) {
                List<UserApplication> userApplications = userAppRepo.findByApplication(application);
                if (!userApplications.isEmpty()) {
                    List<User> users = new ArrayList<>();
                    for (UserApplication userApplication : userApplications) {
                        User user = userApplication.getUser();
                        users.add(user);
                    }
                    return new ResponseEntity<>(users, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        try {
            List<Application> applications = new ArrayList<>();
            appRepo.findAll().forEach(applications::add);
            if (applications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/applications/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable("id") Long applicationId) {
        try {
            Optional<Application> application = appRepo.findById(applicationId);
            if (application.isPresent()) {
                return new ResponseEntity<>(application.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/applications")
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        try {
            Application newApplication = new Application(application.getaJob(), application.getStatus());
            appRepo.save(newApplication);
            return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/applications/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long id,
            @RequestBody Application updatedApplication) {
        Optional<Application> application = appRepo.findById(id);
        if (application.isPresent()) {
            Application existingApplication = application.get();
            existingApplication.setStatus(updatedApplication.getStatus());
            appRepo.save(existingApplication);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable("id") Long applicationId) {
        Optional<Application> application = appRepo.findById(applicationId);
        if (application.isPresent()) {
            appRepo.delete(application.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
