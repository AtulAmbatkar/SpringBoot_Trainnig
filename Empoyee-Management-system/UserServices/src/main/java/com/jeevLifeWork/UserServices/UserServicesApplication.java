package com.jeevLifeWork.UserServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The main entry point for the User Services Spring Boot application.
 * <p>
 * This class is annotated with {@link SpringBootApplication} to enable
 * auto-configuration, component scanning, and configuration properties. It also
 * enables service discovery with the {@link EnableDiscoveryClient} annotation,
 * allowing the application to register with a service registry (e.g., Eureka).
 * </p>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServicesApplication.class, args);
	}

}
