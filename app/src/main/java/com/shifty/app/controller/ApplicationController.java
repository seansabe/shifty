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
import com.shifty.app.model.Job;
import com.shifty.app.model.JobRepository;
import com.shifty.app.model.User;
import com.shifty.app.model.UserRepository;
import com.shifty.app.requests.ApplicationRequest;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationRepository appRepo;

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private UserRepository userRepo;

    // GET ALL APPLICATIONS
    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        try {
            List<Application> applications = new ArrayList<>();
            appRepo.findAll().forEach(applications::add);
            System.out.println(applications.size());
            if (applications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET APPLICATION BY ID
    @GetMapping("/applications/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable(value = "id") Long applicationId) {
        Optional<Application> application = appRepo.findByApplicationId(applicationId);

        if (application.isPresent()) {
            return new ResponseEntity<>(application.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CREATE AN APPLICATION FOR A JOB
    @PostMapping("/applications")
    public ResponseEntity<Application> createApplication(@RequestBody ApplicationRequest application) {
        try {
            Optional<Job> job = jobRepo.findByJobId(application.getJobId());
            Optional<User> user = userRepo.findByUserId(application.getUserId());
            Application newApplication = appRepo.save(new Application(job.get(), user.get(),
                    application.getStatus()));
            return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE AN APPLICATION
    @PutMapping("/applications/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable("id") Long applicationId,
            @RequestBody Application updatedApplication) {
        Optional<Application> application = appRepo.findByApplicationId(applicationId);
        if (application.isPresent()) {
            Application existingApplication = application.get();
            existingApplication.setStatus(updatedApplication.getStatus());
            appRepo.save(existingApplication);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE AN APPLICATION
	@DeleteMapping("/applications/{id}")
	public ResponseEntity<HttpStatus> deleteApplication(@PathVariable("id") long id) {
		try {
			appRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    // GET APPLICATIONS BY JOB ID
    @GetMapping("/applications/job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsByJobId(@PathVariable(value = "jobId") Long jobId) {
        try {
            Optional<Job> job = jobRepo.findByJobId(jobId);
            List<Application> applications = appRepo.findByJob(job.get());
            if (applications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
