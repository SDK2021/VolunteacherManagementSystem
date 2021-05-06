package com.volunteacher.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.volunteacher.app.service.interfaces.EmailService;

@SpringBootApplication
@EnableJpaAuditing  //for working @CreatedDate
public class VolunteacherManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolunteacherManagementSystemApplication.class, args);
	}
	

}
