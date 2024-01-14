package com.patienthubproject.patienthubproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PatientHubProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientHubProjectApplication.class, args);
	}

}
