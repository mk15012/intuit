package com.example.intuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IntuitApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntuitApplication.class, args);
	}

}
