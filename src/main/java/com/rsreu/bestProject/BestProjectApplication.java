package com.rsreu.bestProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestProjectApplication.class, args);
	}

}
