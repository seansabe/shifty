package com.shifty.app;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.shifty.app.model.User;
import com.shifty.app.model.UserRepository;



@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	@Bean
	ApplicationRunner init(UserRepository userRepo) {
		return arg ->{
			userRepo.save(new User("Alexey","Navalny","Prison IK-2, Pokrov city","12345678910","alex.navalny@gmail.com","employer","freeNavalny"));
			userRepo.save(new User("Boris","Nemtsov","Heaven or Hell","12345678910","boris.nemtsov@gmail.com","employee","RIP"));
			userRepo.save(new User("Ilya","Yashin","Prison in Siberia","12345678910","ilya.yashin@gmail.com","employee","freeYashin"));
			userRepo.findAll().forEach(System.out::println);
		};
	}

}
