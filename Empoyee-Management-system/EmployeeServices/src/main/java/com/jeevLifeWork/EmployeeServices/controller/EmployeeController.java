package com.jeevLifeWork.EmployeeServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeevLifeWork.EmployeeServices.dto.ApiResponse;
import com.jeevLifeWork.EmployeeServices.model.Employee;
import com.jeevLifeWork.EmployeeServices.services.IEmployeeService;

/**
 * REST controller for managing {@link Employee} entities.
 * 
 * This controller provides endpoints for CRUD operations on employee resources.
 * It communicates with the service layer through the {@link IEmployeeService}
 * interface to perform these operations.
 * 
 */
@RestController
@RequestMapping("employees")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;

	/**
	 * Retrieves a list of all employees.
	 * 
	 * This endpoint returns a list of all employees. If no employees are found, it
	 * returns a status of {@code 204 No Content}.
	 * 
	 * 
	 * @return a {@link ResponseEntity} containing a list of employees or a message
	 *         indicating an error
	 */
	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> showAllEmployee() {
		try {
			List<Employee> emplist = service.getAllEmployee();
			if (emplist.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return ResponseEntity.ok(emplist);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves a specific employee by their ID.
	 *
	 * This endpoint returns the employee with the specified ID. If no employee is
	 * found, it returns a status of {@code 404 Not Found}.
	 * 
	 * 
	 * @param eid the ID of the employee to retrieve
	 * @return a {@link ResponseEntity} containing the employee's details or a
	 *         message indicating that the employee was not found
	 */
	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> showEmployee(@PathVariable("id") int eid) {
		try {
			Employee employee = service.getEmployeeById(eid);
			return ResponseEntity.ok(employee);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Employee with ID " + eid + " was not found", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Creates a new employee.
	 * 
	 * This endpoint adds a new employee to the system. If the provided employee
	 * data is invalid, it returns a status of {@code 400 Bad Request}.
	 * 
	 * 
	 * @param employee the employee data to create
	 * @return a {@link ResponseEntity} containing a success message or an error
	 *         message if the data is invalid
	 */
	@PostMapping
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		if (employee.getEname() == null) {
			return  ResponseEntity.ok( HttpStatus.BAD_REQUEST);
		}
		String msg = service.addNewEmployee(employee);
		return ResponseEntity.ok(new ApiResponse(msg));
	}

	/**
	 * Updates an existing employee by their ID.
	 * 
	 * This endpoint updates the employee with the specified ID. If the update is
	 * successful, it returns a status of {@code 200 OK}.
	 * 
	 * 
	 * @param id       the ID of the employee to update
	 * @param employee the updated employee data
	 * @return a {@link ResponseEntity} containing a success message
	 */
	@PutMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		String msg = service.updateEmployeeById(id, employee);
		return  ResponseEntity.ok(new ApiResponse(msg));
	}

	/**
	 * Deletes an employee by their ID. This endpoint deletes the employee with the
	 * specified ID. If the deletion is successful, it returns a status of
	 * {@code 200 OK}.
	 * 
	 * @param id the ID of the employee to delete
	 * @return a {@link ResponseEntity} containing a success message
	 */
	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
		String msg = service.deleteEmployee(id);
		return ResponseEntity.ok(new ApiResponse(msg));
	}
}
