package com.jeevLifeWorks.main.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeevLifeWorks.main.entity.Employee;
import com.jeevLifeWorks.main.exception.DepartMentNotFoundException;
import com.jeevLifeWorks.main.exception.EmployeeNotFoundException;
import com.jeevLifeWorks.main.exception.InvalidInputDataException;
import com.jeevLifeWorks.main.repository.IEmployeeRepository;

/**
 * The EmployeeServiceImple class contains business logic for Managing employee.
 * It encapsulates operations such as adding, retrieving, updating, and deleting
 * employees.
 */
@Service
public class EmployeeServiceImple implements IEmployeeService {

	@Autowired
	private IEmployeeRepository emplRepository;

	/**
	 * Adds a new student to the database.
	 *
	 * student the student object to add throws InvalidInputDataException if the
	 * student data is invalid
	 */
	public void addEmployee(Employee employee1) {
		Optional<Employee> employee = emplRepository.findById(employee1.getId());
		if (employee.isPresent()) {
			throw new InvalidInputDataException("Student Id Already exists in the database");
		} else {
			emplRepository.save(employee1);
		}
	}

	/**
	 * Get All student to the database
	 * 
	 * all student that are present into database
	 */
	public List<Employee> getAllEmployees() {
		List<Employee> studentList = new ArrayList<>();
		emplRepository.findAll().forEach(studentList::add);
		return studentList;
	}

	/**
	 * Get Student by Id
	 * 
	 * id The Id match student retrieve student whose id is match
	 * StudentNotFoundException if no student with the given ID exists
	 */
	public Employee getEmployeeById(Long id) {
		Optional<Employee> employee = emplRepository.findById(id);
		if (employee.isPresent()) {
			Employee employee1 = employee.get();
			return employee1;
		} else {
			throw new EmployeeNotFoundException("Student is not present in the databas for givin the Id");
		}

	}

	/**
	 * Update student by their ID from database.
	 * 
	 * id the Id of the student update throws StudentNotFoundException if no student
	 * with the given ID exists
	 */
	public void updateEmployee(Long id, Employee employee) {
		Optional<Employee> employee1 = emplRepository.findById(id);
		if (employee1.isPresent()) {
			Employee employee2 = emplRepository.findById(id).get();
			employee2.setName(employee.getName());
			employee2.setPosition(employee.getPosition());
			employee2.setDepartment(employee.getDepartment());
			employee2.setSalary(employee.getSalary());
			Employee employee3 = emplRepository.save(employee2);
			System.out.println("employee3: " + employee3);
		} else {
			throw new EmployeeNotFoundException("Student is not present in the databas for givin the Id");
		}
	}

	/**
	 * Deletes a student from the database by their ID.
	 *
	 * id the ID of the student to delete return the deleted student throws
	 * StudentNotFoundException if no student with the given ID exists
	 */
	public Employee deletEmployee(Long id) {
		Optional<Employee> employee = emplRepository.findById(id);
		if (employee.isPresent()) {
			emplRepository.deleteById(id);
			Employee employee1 = employee.get();
			return employee1;
		} else {
			throw new EmployeeNotFoundException("Student is not present in the databas for givin the Id");
		}

	}

	/**
	 * get all employee by department map with id departmentId
	 * 
	 */
	public List<Employee> getEmployeeByDepartmentId(int departmentId) {
		List<Employee> emplList = emplRepository.findEmployeeByDepartmentId(departmentId);

		if (emplList == null || emplList.isEmpty()) {
			System.out.println("No employees found for Department ID: " + departmentId);
		} else {
			System.out.println("Found " + emplList.size() + " employees for Department ID: " + departmentId);
		}

		return emplList;
	}

	/**
	 * update Employee newSalary mapping with id
	 */
	public Employee updateEmployeeSalary(Long employeeId, double newSalary) {
		System.out.println("Entering updateEmployeeSalary method with " + employeeId + "And " + newSalary);
		Optional<Employee> optionalEmployee = emplRepository.findById(employeeId);
		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			employee.setSalary(newSalary);
			return emplRepository.save(employee);
		} else {
			throw new EmployeeNotFoundException("Student is not present in the databas for givin the Id");
		}
	}

	/**
	 * totalSalaryByDepartment method get total salary by departments
	 */
	public Double totalSalaryByDepartment(int departmentId) {
		System.out.println("Entering totalSalaryByDepartment() method");

		// Retrieve salaries of employees in the department
		List<Double> empSalary = emplRepository.findSalaryByDepartmentId(departmentId);
		System.out.println("Salaries in department " + departmentId + ": " + empSalary);

		// Calculate total salary
		double totalSalary = 0.0;
		for (double salary : empSalary) {
			totalSalary += salary;
		}

		System.out.println("Total salary for department " + departmentId + ": " + totalSalary);

		return totalSalary;
	}

	/**
	 * findEmployeesByJoiningDateRange service method to find all employees who
	 * joined within a specific date range
	 */
	public List<Employee> findEmployeesByJoiningDateRange(LocalDate startDate, LocalDate endDate) {
		System.out.println("Entering findEmployeesByJoiningDateRange method with startDate: " + startDate
				+ " and endDate: " + endDate);

		List<Employee> employees = emplRepository.findByJoiningDateBetween(startDate, endDate);

		System.out.println("Exiting findEmployeesByJoiningDateRange method");

		return employees;
	}

	/**
	 * updateEmployeePosition method Update Employee Position Based on Experience
	 */
	public Employee updateEmployeePosition(Long empId) {
		System.out.println("Entering updateEmployeePosition method");

		Optional<Employee> optional = emplRepository.findById(empId);
		if (!optional.isPresent()) {
			throw new EmployeeNotFoundException("Employee not found for ID: " + empId);
		}

		Employee emp = optional.get();

		int experience = emplRepository.findEmployeeExperience(empId);
		System.out.println("Experience for employee ID " + empId + ": " + experience);

		if (emp != null) {
			if (experience < 5) {
				emp.setPosition("Junior Developer");
			} else {
				emp.setPosition("Senior Developer");
			}
		} else {
			emp.setPosition("Fresher");
		}

		emplRepository.save(emp);

		System.out.println("Exiting updateEmployeePosition method");
		return emp;
	}

	/**
	 * generating report on employees on the basis of entity class
	 */
	public String generateEmployeeReport(Long employeeId) {
		if (employeeId != null) {
			System.out.println("Entering generateEmployeeReport method with " + employeeId);
			Optional<Employee> optional = emplRepository.findById(employeeId);

//		     Check if the employee exists
			if (!optional.isPresent()) {
				throw new EmployeeNotFoundException("Employee not found for ID: " + employeeId);
			}

			Employee employee = optional.get();

			// Construct the report
			StringBuilder report = new StringBuilder();
			report.append("Employee Report\n");
			report.append("-----------------\n");
			report.append("Employee ID: ").append(employee.getId()).append("\n");
			report.append("Name: ").append(employee.getName()).append("\n");
			report.append("Position: ").append(employee.getPosition()).append("\n");
			report.append("Department: ").append(employee.getDepartment()).append("\n");
			report.append("Salary: ").append(employee.getSalary()).append("\n");

			return report.toString();
		} else {
			return "Error:" + employeeId + "is null ";
		}
	}

	/**
	 * getting employe average salary by the position.
	 */
	public double averageSalaryByPosition(String position) {
		System.out.println("Entering averageSalaryByPosition() method");

		double average = 0.0;

		if (position != null) {
			double total = 0.0;
			List<Employee> emplList = emplRepository.findAll();
			String employeePosition;

			int count = 0;

			for (Employee employee : emplList) {
				employeePosition = employee.getPosition();

				if (employeePosition != null && position.equals(employeePosition)) {
					total += employee.getSalary();
					count++;
				}
			}

			if (count > 0) {
				average = total / count;
			}
		}

		System.out.println("Exiting averageSalaryByPosition() method");
		return average;
	}

	/**
	 * checkPromotionEligibility to check the promotion eligibility or not basis on
	 * performance score and years of service.
	 */
	public String checkPromotionEligibility(Long employeeId) {
		System.out.println("Entering checkPromotionEligibility method");
		// Check if employee exists
		Employee employee = emplRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for ID: " + employeeId));

		// Proceed with promotion eligibility check
		Long count = emplRepository.findPromotionEligibility(employeeId);

		System.out.println("Promotion eligibility count: " + count);

		if (count != null && count > 0) {
			return "Eligible for promotion";
		} else {
			return "Not eligible for promotion";
		}
	}

	/**
	 * getDepartmentsRankedByAveragePerformanceScore to map the rank departments
	 * based on average employee performance scores.
	 */
	public Map<String, Double> getDepartmentsRankedByAveragePerformanceScore() {
		System.out.println("Entering getDepartmentsRankedByAveragePerformanceScore method");

		List<Object[]> results = emplRepository.findDepartmentsRankedByAveragePerformanceScore();
		Map<String, Double> rankedDepartments = new LinkedHashMap<>();

		for (Object[] result : results) {
			String department = (String) result[0];
			Double avgPerformanceScore = ((Number) result[1]).doubleValue();
			rankedDepartments.put(department, avgPerformanceScore);
		}

		System.out.println("Return ranked departments by average performance score");

		return rankedDepartments;
	}

	/**
	 * updateDepartmentBudget method to Update Department Budget Based on Employee
	 * Count
	 */
	public double updateDepartmentBudget(Integer departmentId) {
		if (departmentId == null) {
			System.out.println("Error: departmentId cannot be null.");
			return 0.0;
		}

		System.out.println("Entering updateDepartmentBudget method with departmentId: " + departmentId);

		double budgetAmount = 0.0;
		List<Employee> empList = emplRepository.findEmployeeByDepartmentId(departmentId);
		System.out.println("empList" + empList);

		if (empList.isEmpty()) {
			throw new DepartMentNotFoundException("Department Id not found " + departmentId);

		}

		int employeeCount = empList.size();
		System.out.println("Number of employees in department " + departmentId + ": " + employeeCount);

		if (employeeCount >= 100) {
			budgetAmount = 1000 + (employeeCount * 900);
		} else if (employeeCount > 30) {
			budgetAmount = 1000 + (employeeCount * 700);
		} else {
			budgetAmount = 1000 + (employeeCount * 500);
		}

		System.out.println("Calculated budget amount for department " + departmentId + ": " + budgetAmount);
		emplRepository.saveAll(empList);

		System.out.println("Exiting updateDepartmentBudget method");
		return budgetAmount;
	}

	/**
	 * highestPaidEmployees this method return Top highest paid employees in company
	 */
	public List<Employee> highestPaidEmployees(int number) {
		System.out.println("Entering highestPaidEmployees method");

		List<Employee> empList = (List<Employee>) emplRepository.findAll();
		List<Employee> highestPaidEmployees = new ArrayList<>();

		if (number > 0) {
			highestPaidEmployees = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
					.limit(number).collect(Collectors.toList());
			System.out.println("Returning " + number + " highest paid employees");
			return highestPaidEmployees;
		}

		System.out.println("Number must be greater than 0, returning empty list");
		return Collections.emptyList();
	}

	/**
	 * checkDuplicateEmployeeByEmail this method check any one are duplicate based
	 * on email
	 */
	public List<Employee> checkDuplicateEmployeeByEmail() {
		System.out.println("Entering checkDuplicateEmployeeByEmail method");

		// Retrieve all email addresses from the repository
		List<String> emailList = emplRepository.findByEmail();
		System.out.println("emailList: " + emailList);

		// Set to store unique email addresses
		Set<String> uniqueEmails = new HashSet<>();

		// List to store employees with duplicate emails
		List<Employee> duplicatesEmployee = new ArrayList<>();

		for (String email : emailList) {
			// Check if the email is already in the set (i.e., duplicate)
			if (!uniqueEmails.add(email)) {
				// Retrieve employees with the duplicate email
				List<Employee> employeesWithDuplicateEmail = emplRepository.findByEmail(email);
				duplicatesEmployee.addAll(employeesWithDuplicateEmail);
			}
		}

		System.out.println("Exiting checkDuplicateEmployeeByEmail method");

		return duplicatesEmployee;
	}

}