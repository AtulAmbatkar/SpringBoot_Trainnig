
package com.jeevLifeWorks.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * The Employee class represents an entity class for JPA It is Mapped to the
 * "employeees" table in the database
 */
@Entity
@Table(name = "employees")
public class Employee {

	/**
	 * @Id annotation for mapped with Primary key in the database table
	 * @@GeneratedValue Defines the types of primary key generation strategies
	 * @Column annotation mapped with column in the database table
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	/**
	 * @NotNull annotated the element must not be null nor empty.Mapped with one
	 *          column it validate with rest API
	 */
	@NotNull(message = "Employee name not be null")
	@Column(name = "name")
	private String name;

	@Column(name = "position")
	private String position;

	@Column(name = "department")
	private String department;

	@NotNull(message = "Salary Not be Null")
	@Min(value = 1, message = "Salary must be at least 1")
	@Column(name = "salary")
	private double salary;

	public Employee() {
		super();
	}
	
	// ParamConstructor for the creating object with valid information
	public Employee(Long id, @NotNull(message = "Employee name not be null") String name, String position,
			String department,
			@NotNull(message = "Salary Not be Null") @Min(value = 1, message = "Salary must be at least 1") double salary,
			Long departmentId) {

		this.id = id;
		this.name = name;
		this.position = position;
		this.department = department;
		this.salary = salary;
//			this.departmentId = departmentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(
			@NotNull(message = "Salary Not be Null") @Min(value = 1, message = "Salary must be at least 1") double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", position=" + position + ", department=" + department
				+ ", salary=" + salary + "]";
	}

}
