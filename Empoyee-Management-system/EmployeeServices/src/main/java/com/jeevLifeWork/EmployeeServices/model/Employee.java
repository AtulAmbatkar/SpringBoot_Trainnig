package com.jeevLifeWork.EmployeeServices.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents an Employee entity in the system. This class maps to the
 * "Rest_Employee" table in the database and includes fields for employee ID,
 * name, department, position, and salary.
 */
@Entity
@Table(name = "Rest_Employee")
public class Employee {

	// ID, name, department, position, and salary.

	/**
	 * Employee Id This field is automatically generated using a sequence generator.
	 */
	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "EId_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer eid;

	/**
	 * Employee name This field cannot be null and has a maximum length of 20
	 * characters.
	 */
	@Column(length = 20, nullable = false)
	private String ename;

	/**
	 * Employee Department This Field cannot be null and has max lenght of 20
	 */
	@Column(length = 20, nullable = false)
	private String department;

	/**
	 * Employee Position This field has a maximum length of 20 characters.
	 */
	@Column(length = 20)
	private String position;

	/**
	 * Employee Salary This field cannot be null and has a maximum length of 30
	 * characters.
	 */
	@Column(length = 30, nullable = false)
	private Double salary;

	/**
	 * @param Constructor for the employee Id will be generated sequentially
	 * @param ename
	 * @param department
	 * @param position
	 * @param salary
	 */
	public Employee(String ename, String department, String position, Double salary) {
		super();
		this.ename = ename;
		this.department = department;
		this.position = position;
		this.salary = salary;
	}

	public Employee() {
		super();
	}

	/**
	 * Setters and Getters method for the accessing data
	 * 
	 * @return
	 */
	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	/**
	 * Returns a string representation of the employee.
	 * 
	 * return a string containing the employee's details
	 */
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", department=" + department + ", position=" + position
				+ ", salary=" + salary + "]";
	}

}
