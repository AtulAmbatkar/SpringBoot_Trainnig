package com.jeevLifeworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeevLifeworks.entity.Student;

/**
 * StudentRepository Interface represent the repository that interact with database 
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
