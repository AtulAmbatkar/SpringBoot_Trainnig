package com.jeevLifeWork.EmployeeServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The main entry point for the Employee Services Spring Boot application.
 * <p>
 * This class is annotated with {@link SpringBootApplication} to enable
 * auto-configuration, component scanning, and configuration properties. It also
 * enables service discovery with the {@link EnableDiscoveryClient} annotation,
 * allowing the application to register with a service registry (e.g., Eureka).
 * </p>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServicesApplication.class, args);
	}

}
