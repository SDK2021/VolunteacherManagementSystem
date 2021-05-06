package com.volunteacher.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  //for working @CreatedDate
public class VolunteacherManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolunteacherManagementSystemApplication.class, args);
	}
	

}
