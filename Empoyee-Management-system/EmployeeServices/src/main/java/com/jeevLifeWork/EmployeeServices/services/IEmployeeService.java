package com.jeevLifeWork.EmployeeServices.services;

import java.util.List;

import com.jeevLifeWork.EmployeeServices.model.Employee;

/**
 * Service interface for managing {@link Employee} entities.
 * <p>
 * This interface defines the contract for business logic operations related to
 * employee management. It provides methods for creating, retrieving, updating,
 * and deleting employee records.
 * </p>
 */
public interface IEmployeeService {

	/**
	 * Adds a new employee to the system.
	 * <p>
	 * This method is responsible for persisting a new employee record.
	 * </p>
	 * 
	 * @param emp the employee to be added
	 * @return a message indicating the result of the add operation, including the
	 *         newly created employee's ID
	 */
	public String addNewEmployee(Employee emp);

	/**
	 * Retrieves all employees from the system.
	 * <p>
	 * This method returns a list of all employee records.
	 * </p>
	 * 
	 * @return a list of all employees
	 */
	public List<Employee> getAllEmployee();

	/**
	 * Retrieves a specific employee by their ID.
	 * <p>
	 * This method finds and returns the employee with the specified ID.
	 * </p>
	 * 
	 * @param id the ID of the employee to retrieve
	 * @return the employee with the specified ID
	 * @throws IllegalArgumentException if no employee with the given ID is found
	 */
	public Employee getEmployeeById(int id);

	/**
	 * Updates an existing employee record. This method updates the details of an
	 * employee with the specified ID using the provided employee data.
	 * 
	 * @param id       the ID of the employee to update
	 * @param employee the updated employee data
	 * @return a message indicating the result of the update operation
	 */
	public String updateEmployeeById(int id, Employee employee);

	/**
	 * Deletes an employee record. This method deletes the employee with the
	 * specified ID from the system.
	 * 
	 * @param id the ID of the employee to delete
	 * @return a message indicating the result of the delete operation
	 */
	public String deleteEmployee(int id);
}
