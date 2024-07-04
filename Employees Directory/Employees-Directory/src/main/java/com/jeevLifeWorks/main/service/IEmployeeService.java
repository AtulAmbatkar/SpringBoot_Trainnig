package com.jeevLifeWorks.main.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.jeevLifeWorks.main.entity.Employee;

/**
 * The {@code IEmployeeService} interface provides abstraction for operations
 * related to employees. It defines methods that implementing in its implemented
 * class to perform the operation.
 */
public interface IEmployeeService {

	public void addEmployee(Employee employee1);

	public List<Employee> getAllEmployees();

	public Employee getEmployeeById(Long id);

	public void updateEmployee(Long id, Employee employee);

	public Employee deletEmployee(Long id);

	public List<Employee> getEmployeeByDepartmentId(int departmentId);

	public Employee updateEmployeeSalary(Long employeeId, double newSalary);

	public Double totalSalaryByDepartment(int departmentId);

	public List<Employee> findEmployeesByJoiningDateRange(LocalDate startDate, LocalDate endDate);

	public Employee updateEmployeePosition(Long empId);

	public String generateEmployeeReport(Long employeeId);

	public double averageSalaryByPosition(String position);

	public String checkPromotionEligibility(Long employeeId);

	public Map<String, Double> getDepartmentsRankedByAveragePerformanceScore();

	public double updateDepartmentBudget(Integer departmentId);

	public List<Employee> highestPaidEmployees(int number);

	public List<Employee> checkDuplicateEmployeeByEmail();
}
