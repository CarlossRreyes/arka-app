package com.arka.app_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class AppServicesApplication {


	private static final Logger logger =   LoggerFactory.getLogger(AppServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppServicesApplication.class, args);
		logger.info("RUN ARKA SERVICES IN PORT 4041");
	}

}
