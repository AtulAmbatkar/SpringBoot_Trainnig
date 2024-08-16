package com.jeevLifeWork.EmployeeServices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeevLifeWork.EmployeeServices.model.Employee;
import com.jeevLifeWork.EmployeeServices.repository.IEmployeeRepository;

/**
 * Service implementation for managing {@link Employee} entities.
 *
 * This service class implements the {@link IEmployeeService} interface and
 * provides business logic for CRUD operations on employee resources.
 * 
 */
@Service
public class EmployeeServiceImple implements IEmployeeService {

	@Autowired
	private IEmployeeRepository empRepository;

	/**
	 * Adds a new employee to the system.
	 * 
	 * This method saves the given employee and returns a success message including
	 * the newly created employee's ID.
	 * 
	 * @param emp the employee to be added
	 * @return a message indicating that the employee was added successfully
	 */
	@Override
	public String addNewEmployee(Employee emp) {
		System.out.println("EmployeeServiceImple.addNewEmployee()");
		int empId = empRepository.save(emp).getEid();
		return "Employee Added successfully with id " + empId;
	}

	/**
	 * Retrieves a list of all employees.
	 *
	 * This method fetches and returns all employees stored in the database.
	 * 
	 * @return a list of all employees
	 */
	@Override
	public List<Employee> getAllEmployee() {
		System.out.println("EmployeeServiceImple.getAllEmployee()");
		return empRepository.findAll();
	}

	/**
	 * Retrieves an employee by their ID.
	 * 
	 * This method finds and returns the employee with the specified ID. If no
	 * employee is found with that ID, it throws an
	 * {@link IllegalArgumentException}.
	 * 
	 * 
	 * @param id the ID of the employee to retrieve
	 * @return the employee with the specified ID
	 * @throws IllegalArgumentException if no employee with the given ID is found
	 */
	@Override
	public Employee getEmployeeById(int id) {
		System.out.println("EmployeeServiceImple.getEmployeeById()");
		return empRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Employee with ID " + id + " was not found."));
	}

	/**
	 * Updates an existing employee by their ID.
	 * 
	 * This method updates the employee with the specified ID using the provided
	 * employee data. If the employee is not found, it returns a message indicating
	 * that the employee was not found.
	 * 
	 * 
	 * @param id       the ID of the employee to update
	 * @param employee the updated employee data
	 * @return a message indicating the result of the update operation
	 */
	@Override
	public String updateEmployeeById(int id, Employee employee) {
		System.out.println("EmployeeServiceImple.updateEmployeeById()");
		Optional<Employee> empOptional = empRepository.findById(id);
		if (empOptional.isPresent()) {
			empRepository.save(employee);
			return "Employee is updated";
		}
		return "Employee is not found";
	}

	/**
	 * Deletes an employee by their ID.
	 * 
	 * This method deletes the employee with the specified ID from the database. If
	 * the employee is not found, it returns a message indicating that the employee
	 * was not found for deletion.
	 * 
	 * 
	 * @param id the ID of the employee to delete
	 * @return a message indicating the result of the delete operation
	 */
	@Override
	public String deleteEmployee(int id) {
		System.out.println("EmployeeServiceImple.deleteEmployee()");
		Optional<Employee> empOptional = empRepository.findById(id);
		if (empOptional.isPresent()) {
			empRepository.deleteById(id);
			return "Employee is deleted with id " + id;
		}
		return "Employee is not found for delete operation";
	}
}
