package com.shifty.app;

import java.time.LocalDate;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.shifty.app.model.Job;
import com.shifty.app.model.JobRepository;
import com.shifty.app.model.User;
import com.shifty.app.model.UserRepository;



@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	@Bean
	ApplicationRunner init(UserRepository userRepo, JobRepository jobRepo) {
		return arg ->{
			
			User user1 = new User("Boris","Nemtsov","Heaven or Hell","1234567891","boris.nemtsov@gmail.com","employee","RIP");

			
			userRepo.save(new User("Alexey","Navalny","Prison IK-2, Pokrov city","1365125551","alex.navalny@gmail.com","employer","freeNavalny"));
			userRepo.save(user1);
			userRepo.save(new User("Ilya","Yashin","Prison in Siberia","1234567891","ilya.yashin@gmail.com","employee","freeYashin"));
			userRepo.findAll().forEach(System.out::println);
			
			
			
			jobRepo.save(new Job(user1,"First Job",LocalDate.of(2022, 2, 14),23.25,"Private course","This is just an example",LocalDate.of(2022, 2, 14),LocalDate.of(2022, 2, 14)));
			jobRepo.findAll().forEach(System.out::println); 
			};
		};
	}


