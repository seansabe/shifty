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
import org.springframework.web.bind.annotation.RestController;
import com.shifty.app.model.Job;
import com.shifty.app.model.JobRepository;

import com.shifty.app.model.User;
import com.shifty.app.model.UserRepository;

import com.shifty.app.requests.JobPostingRequest;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class JobController {

	@Autowired
	private JobRepository jobRepo;

	@Autowired
	private UserRepository userRepo;

	// GET ALL JOBS
	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> getAllJobs() {
		try {
			List<Job> jobs = new ArrayList<>();
			jobRepo.findAll().forEach(jobs::add);
			if (jobs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(jobs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// CREATE A JOB
	@PostMapping("/jobs")
	public ResponseEntity<Job> createJob(@RequestBody JobPostingRequest jobPosting) {
		try {
			Optional<User> user = userRepo.findByUserId(jobPosting.getUserId());
			System.out.println(user);
			Job newJob = new Job(user.get(),
					jobPosting.getJob().getTitle(), jobPosting.getJob().getPostingDate(),
					jobPosting.getJob().getHourRate(),
					jobPosting.getJob().getKindOfJob(), jobPosting.getJob().getDescription(),
					jobPosting.getJob().getJobStartDate(), jobPosting.getJob().getJobFinishDate());
			jobRepo.save(newJob);
			return new ResponseEntity<>(newJob, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// DELETE A JOB
	@DeleteMapping("/jobs/{jobId}")
	public ResponseEntity<Job> deleteJob(@PathVariable("jobId") long jobId) {
		try {
			Optional<Job> job = jobRepo.findByJobId(jobId);
			if(job.isPresent()) {
				jobRepo.deleteById(jobId);
				System.out.println("Job with id " + jobId + " deleted successfully");
				return new ResponseEntity<>(HttpStatus.OK);

			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	}
}
