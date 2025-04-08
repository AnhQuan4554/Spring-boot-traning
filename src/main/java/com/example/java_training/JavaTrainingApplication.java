package com.example.java_training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JavaTrainingApplication {

	public static void main(String[] args) {

		SpringApplication.run(JavaTrainingApplication.class, args);
		System.out.println("Running Success");
	}

}
