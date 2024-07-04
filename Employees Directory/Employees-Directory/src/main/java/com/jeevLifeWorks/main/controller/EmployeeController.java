package com.jeevLifeWorks.main.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeevLifeWorks.main.entity.Employee;
import com.jeevLifeWorks.main.service.IEmployeeService;

/**
 * EmployeeController class represent the controller on the Employee operation
 * mapped with JPA This class provide endPoint to create,retrieve,update and
 * delete employee operation and other view employee details.
 */
@RestController
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	/**
	 * Add new employee to the database
	 * 
	 * @param the employees to be added
	 * @return ResponseEntity contains the created employee and httpStatus created
	 */
	@PostMapping("/employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(employee);
	}

	/**
	 * Retrieve all employees whose present in the database
	 * 
	 * @return list contain all employees list
	 */
	@GetMapping("/employees")
	public List<Employee> getAllEmplyee() {
		return employeeService.getAllEmployees();
	}

	/**
	 * Retrieve a employee by their Id
	 * 
	 * @param id The Id of the employee retrieve
	 * @return employee contain the Id that employee are return
	 */
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return employee;
	}

	/**
	 * Update employee by their Id
	 * 
	 * @param employee The employee to be update
	 * @param id       the Id of the employee present into database
	 * @return ResponseEntity contains httpstatus UPGRADE_REQUIRED and employee body
	 */
	@PutMapping("/employees/{id}")
	public @ResponseBody ResponseEntity<Employee> updateEmployeeByid(@RequestBody Employee employee,
			@PathVariable Long id) {
		employeeService.updateEmployee(id, employee);
		return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(employee);
	}

	/**
	 * Delete employee by their Id
	 * 
	 * @param id the employee are Delete
	 * @return
	 * @return ResponseEntity contains the httpstatus ok
	 */
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.deletEmployee(id);
		return ResponseEntity.ok(employee);
	}

	/**
	 * get all employee by department
	 * 
	 * @param departmentId
	 * @return ResponseEntity
	 */
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable int departmentId) {
		List<Employee> employees = employeeService.getEmployeeByDepartmentId(departmentId);
		if (employees == null || employees.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(employees);
		}
		return ResponseEntity.ok(employees);
	}

	/**
	 * update Employee with salary
	 * 
	 * @param employeeId
	 * @param salaryRequest
	 * @return
	 */
	@PutMapping("/employee/{employeeId}/salary")
	public ResponseEntity<Employee> updateEmployeeSalary(@PathVariable Long employeeId,
			@RequestBody Map<String, Double> salaryRequest) {
		double newSalary = salaryRequest.get("newSalary");
		Employee updatedEmployee = employeeService.updateEmployeeSalary(employeeId, newSalary);
		if (updatedEmployee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(updatedEmployee);
	}

	/**
	 * map to getting total salary by department
	 * 
	 * @param department_id to get id on that department
	 * @return return ResponseEntity status ok.
	 */
	@GetMapping("/employee/total-salary/{department_id}")
	public ResponseEntity<Double> getTotalSalaryByDepartment(@PathVariable int department_id) {
		Double totalSalary = (Double) employeeService.totalSalaryByDepartment(department_id);
		return ResponseEntity.ok(totalSalary);
	}

	/**
	 * get Employees by specific data ranges
	 * 
	 * @param startDate start_date on localDate
	 * @param endDate   snd_date to get between this date
	 * @return ResponseEntity contains the ok
	 */
	@GetMapping("/employees/joining_date")
	public ResponseEntity<List<Employee>> getJoiningDateWiseEmployees(@RequestParam LocalDate startDate,
			@RequestParam LocalDate endDate) {
		List<Employee> emplList = employeeService.findEmployeesByJoiningDateRange(startDate, endDate);
		return ResponseEntity.ok(emplList);
	}

	/**
	 * map with emplId updating the employee position based on the experience
	 * 
	 * @param emplId
	 * @return ResponseEntity contains the ok
	 */
	@PostMapping("/employee/{emplId}")
	public ResponseEntity<Employee> updteEmployeeByexperience(@PathVariable Long emplId) {
		Employee empl = employeeService.updateEmployeePosition(emplId);
		return ResponseEntity.ok(empl);
	}

	/**
	 * Getting employee report to map with emplId id
	 * 
	 * @param emplId
	 * @return ResponseEntity contains the ok
	 */
	@GetMapping("/employees/report/{emplId}")
	public ResponseEntity<String> getEmployeeReport(@PathVariable Long emplId) {
		String report = employeeService.generateEmployeeReport(emplId);
		return ResponseEntity.ok(report);
	}

	/**
	 * to get salary by the position
	 * 
	 * @param position
	 * @return ResponseEntity contains the ok
	 */
	@GetMapping("/employees/average_salary/{position}")
	public ResponseEntity<Double> getaverageSalaryByPosition(@PathVariable String position) {
		double averageSalary = employeeService.averageSalaryByPosition(position);
		return ResponseEntity.ok(averageSalary);
	}

	/**
	 * to check eligible or not for the promotion
	 * 
	 * @param emplId
	 * @return ResponseEntity contains the ok
	 */
	@GetMapping("/employees/checkElegibility/{emplId}")
	public ResponseEntity<String> getPromotionElibgilityOrNot(@PathVariable Long emplId) {
		String checkEligibility = employeeService.checkPromotionEligibility(emplId);
		return ResponseEntity.ok(checkEligibility);
	}

	/**
	 * get all department rank wise
	 * 
	 * @return ResponseEntity contains the OK
	 */
	@GetMapping("/employee/departmentRank")
	public ResponseEntity<String> getDeparmrntByRank() {
		Map<String, Double> rankList = employeeService.getDepartmentsRankedByAveragePerformanceScore();
		String responseMessage = " List of departments ranked by average performance score \n" + rankList;
		return ResponseEntity.ok(responseMessage);
	}

	/**
	 * get department budget based on employee number
	 * 
	 * @param departmentId
	 * @return ResponseEntity contains the OK
	 */
	@GetMapping("/employee/departmentBudget/{departmentId}")
	public ResponseEntity<String> getDeprtmentBudget(@PathVariable int departmentId) {
		double updatedBudgetAmount = employeeService.updateDepartmentBudget(departmentId);
		String responseMessage = "The updated budget for department ID " + departmentId + " is " + updatedBudgetAmount;
		return ResponseEntity.ok(responseMessage);
	}

	/**
	 * get Top number of Highest paid employee in the company
	 * 
	 * @param number this number of employee is highest paid
	 * @return ResponseEntity contains the OK
	 */
	@GetMapping("/employee/highestPaidEmployees/{number}")
	public ResponseEntity<String> getTopHighestPaidEmployees(@PathVariable int number) {
		List<Employee> empList = employeeService.highestPaidEmployees(number);
		String responseMessage = "List of top " + number + " highest-paid employees in a company\n" + empList;
		return ResponseEntity.ok(responseMessage);

	}

	/**
	 * get duplicate employee map with email
	 * 
	 * @return ResponseEntity contains the OK
	 */
	@GetMapping("/employees/duplicateEmployeeByEmail")
	public ResponseEntity<String> grtDuplicateEmployeeByEmial() {
		List<Employee> duplicateEmployee = employeeService.checkDuplicateEmployeeByEmail();
		String responseMessage = "List of duplicate employees \n" + duplicateEmployee;
		return ResponseEntity.ok(responseMessage);
	}

}
