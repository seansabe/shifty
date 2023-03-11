package com.shifty.app.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shifty.app.model.Job;
import com.shifty.app.model.JobRepository;

@CrossOrigin(origins = "hhtp://localhost:8081")
@RestController
@RequestMapping("/api")
public class JobController {

	@Autowired
	private JobRepository jobRepo;

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
	public ResponseEntity<Job> createJob(@RequestBody Job job) {
		try {
			Job newJob = new Job(job.getUser(),
					job.getTitle(), job.getPostingDate(), job.getHourRate(),
					job.getKindOfJob(), job.getDescription(), job.getJobStartDate(), job.getJobFinishDate());
			jobRepo.save(newJob);
			return new ResponseEntity<>(newJob, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
