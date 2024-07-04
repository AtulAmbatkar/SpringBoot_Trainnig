package com.jeevLifeworks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeevLifeworks.entity.Student;
import com.jeevLifeworks.service.StudentService;

import jakarta.validation.Valid;

/**
 * StudentController class represent the controller on the Student operation
 * mapped with JPA This class provide endPoint to create,retrieve,update and
 * delete student operation.
 */
@RestController
@RequestMapping("/api")
@Validated
public class StudentController {

	@Autowired
	private StudentService studentService;

	/**
	 * Add new student to the database
	 * 
	 * @param student the student to be added
	 * @return ResponseEntity contains the created student and httpStatus created
	 */
	@PostMapping("/students")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
		studentService.addStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(student);
	}

	/**
	 * Retrieve all student whose present in the database
	 * 
	 * @return list contain all student list
	 */
	@GetMapping("/students")
	public List<Student> getAllStudent() {
		return studentService.getAllStudents();
	}

	/**
	 * Retrieve a student by their Id
	 * 
	 * @param id The Id of the student retrieve
	 * @return student contain the Id that student are return
	 */
	@GetMapping("/students/{id}")
	public Student getStudentById(@PathVariable Long id) {
		Student student = studentService.getStudentById(id);
		return student;
	}

	/**
	 * Update student by their Id
	 * 
	 * @param student The student to be update
	 * @param id      the Id of the student present into database
	 * @return ResponseEntity contains httpstatus UPGRADE_REQUIRED and student body
	 */
	@PutMapping("/students/{id}")
	public @ResponseBody ResponseEntity<Student> updateStudentByid(@Valid @RequestBody Student student,
			@PathVariable Long id) {
		studentService.updateStudent(id, student);
		return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(student);
	}

	/**
	 * Delete student by their Id
	 * 
	 * @param id the Student are Delete
	 * @return ResponseEntity contains the httpstatus ok
	 */
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Student> deleteStudentById(@PathVariable Long id) {
		Student student = studentService.deleStudent(id);
		return ResponseEntity.ok(student);
	}
}
