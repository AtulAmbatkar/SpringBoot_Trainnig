package com.jeevLifeWork.service_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Entry point for the Service Gateway application. This class is responsible
 * for launching the Spring Boot application.
 */
@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceGatewayApplication.class, args);
	}

}
