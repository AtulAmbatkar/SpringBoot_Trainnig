package com.jeevLifeworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeevLifeworks.entity.Student;

/**
 * The StudentRepository interface provides CRUD operations for the Student entity.
 * It interacts with the database using JPA.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
