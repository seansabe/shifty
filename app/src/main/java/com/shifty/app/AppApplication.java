package com.shifty.app;

import java.time.LocalDate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.shifty.app.model.Application;
import com.shifty.app.model.ApplicationRepository;
import com.shifty.app.model.Job;
import com.shifty.app.model.JobRepository;
import com.shifty.app.model.User;
import com.shifty.app.model.UserApplication;
import com.shifty.app.model.UserApplicationRepository;
import com.shifty.app.model.UserRepository;
import com.shifty.app.model.JobType;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	ApplicationRunner init(UserRepository userRepo, JobRepository jobRepo, ApplicationRepository appRepo,
			UserApplicationRepository userAppRepo) {
		return arg -> {

			// CREATE USER EXAMPLE
			User poster = new User("Sergio", "Salazar", "Surrey, BC", "1365125551",
					"sergio@outlook.com", User.POSTER, "1234");
			User buster = new User("Neslihan", "Turpcu", "Burnaby, BC", "1234567891", "neslihan@gmail.com",
					User.BUSTER, "1234");

			userRepo.save(poster);
			userRepo.save(buster);

			// CREATE JOB LISTING EXAMPLE
			Job job1 = new Job(poster, "Cleaner wanted ASAP!", LocalDate.of(2023, 2, 24),
					20.00, JobType.CLEANING,
					"Looking for someone who can clean my garage next month. Willing to lift more than 20 kilograms",
					LocalDate.of(2023, 03, 10), LocalDate.of(2023, 3, 10));
			Job job2 = new Job(poster, "Looking for someone who do groceries", LocalDate.of(2023, 2, 24),
					18.00, JobType.GROCERIES,
					"Looking for someone who can do my groceries next month. Willing to lift more than 20 kilograms",
					LocalDate.of(2023, 03, 1), LocalDate.of(2023, 3, 1));

			jobRepo.save(job1);
			jobRepo.save(job2);

			// CREATE JOB APPLICATION EXAMPLE
			Application app1 = new Application(job1, Application.APPLIED);
			job1.addApplication(app1);
			appRepo.save(app1);
			UserApplication userApp = new UserApplication(buster, app1);
			buster.addUserApplication(userApp);
			app1.addUserApplication(userApp);
			userAppRepo.save(userApp);

			// userRepo.findAll().forEach(System.out::println);
			// jobRepo.findAll().forEach(System.out::println);
		};
	};
}