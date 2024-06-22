package com.jeevLifeworks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.InvalidApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeevLifeworks.entity.Student;
import com.jeevLifeworks.exception.InvalidInputDataException;
import com.jeevLifeworks.exception.StudentNOtFoundException;
import com.jeevLifeworks.repository.StudentRepository;

/**
 * The StudentService class contains business logic for Managing student. It
 * encapsulates operations such as adding, retrieving, updating, and deleting
 * students.
 */
@Service
public class StudentService {

	@Autowired
	public StudentRepository studentRepository;

	/**
	 * Adds a new student to the database.
	 *
	 * @param student the student object to add
	 * @throws InvalidInputDataException if the student data is invalid
	 */
	public void addStudent(Student student) {
		Optional<Student> student1 = studentRepository.findById(student.getId());
		if (student1.isPresent()) {
			throw new InvalidInputDataException("Student Id Already exists in the database");
		} else {
			studentRepository.save(student);
		}
	}

	/**
	 * Get All student to the database
	 * 
	 * @return all student that are present into database
	 */
	public List<Student> getAllStudents() {
		List<Student> studentList = new ArrayList<>();
		studentRepository.findAll().forEach(studentList::add);
		return studentList;
	}

	/**
	 * Get Student by Id
	 * 
	 * @param id The Id match student retrieve
	 * @return student whose id is match
	 * @throws StudentNotFoundException if no student with the given ID exists
	 */
	public Student getStudentById(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			Student student1 = student.get();
			return student1;
		} else {
			throw new StudentNOtFoundException("Student is not present in the databas for givin the Id");
		}

	}

	/**
	 * Update student by their ID from database.
	 * 
	 * @param id the Id of the student update
	 * @throws StudentNotFoundException if no student with the given ID exists
	 */
	public void updateStudent(Long id, Student student) {
		Optional<Student> student1 = studentRepository.findById(id);
		if (student1.isPresent()) {
			Student student2 = studentRepository.findById(id).get();
			student2.setName(student.getName());
			student2.setAge(student.getAge());
			student2.setGrade(student.getGrade());
			student2.setAddress(student.getAddress());
			Student student3 = studentRepository.save(student2);
			System.out.println("student3: " + student3);
		} else {
			throw new StudentNOtFoundException("Student is not present in the databas for givin the Id");
		}
	}

	/**
	 * Deletes a student from the database by their ID.
	 *
	 * @param id the ID of the student to delete
	 * @return the deleted student
	 * @throws StudentNotFoundException if no student with the given ID exists
	 */
	public Student deleStudent(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			studentRepository.deleteById(id);
			Student student1 = student.get();
			return student1;
		} else {
			throw new StudentNOtFoundException("Student is not present in the databas for givin the Id");
		}

	}
}
