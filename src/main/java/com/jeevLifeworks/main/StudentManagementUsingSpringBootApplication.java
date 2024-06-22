package com.jeevLifeworks.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jeevLifeworks.controller.StudentController;
import com.jeevLifeworks.entity.Student;
import com.jeevLifeworks.service.StudentService;

/**
 * StudentManagementUsingSpringBootApplication class represent the main class of
 * the application here main method are present
 */
@SpringBootApplication
@EntityScan(basePackages = "com.jeevLifeworks.entity")
@ComponentScan(basePackages = { "com.jeevLifeworks.controller", "com.jeevLifeworks.service",
		"com.jeevLifeworks.exception" })
//@ComponentScan(basePackages = "com.jeevLifeworks.service")
@EnableJpaRepositories(basePackages = "com.jeevLifeworks.repository")
public class StudentManagementUsingSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementUsingSpringBootApplication.class, args);
	}
}
