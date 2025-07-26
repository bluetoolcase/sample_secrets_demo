package com.bluetoolcase.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bluetoolcase.demo.datalayer.UserService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ApplicationRunner initRunner(final UserService userService){
		return args -> {
			userService.createUser("galjoey", "galjoey@gmail.com");
			userService.createUser("jgaleamt", "jgaleamt@outlook.com");
			userService.createUser("toastmasterjoegalea", "toastmasterjoegalea@gmail.com");
		};
	}

}
