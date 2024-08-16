package com.example.service_register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Entry point for the Service Registration application. This application acts
 * as a Eureka Server for service discovery.
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegisterApplication.class, args);
	}

}
