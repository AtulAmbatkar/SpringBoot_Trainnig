package com.jeevLifeWork.EmployeeServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeevLifeWork.EmployeeServices.model.Employee;

/**
 * Repository interface for accessing Employee entities. This interface extends
 * JpaRepository, which provides basic CRUD operations and query methods for the
 * Employee entity. By extending JpaRepository, this interface inherits methods
 * for saving, deleting, and finding Employee entities, as well as custom query
 * methods if defined.
 * 
 * @see JpaRepository
 * @see Employee
 */
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
