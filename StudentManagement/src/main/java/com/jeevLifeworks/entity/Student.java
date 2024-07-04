package com.jeevLifeworks.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

/**
 * The Student class represents an entity class for JPA It is Mapped to the
 * "students" table in the database
 */

@Entity
@Table(name = "students")
public class Student {

	/**
	 * @Id annotation for mapped with Primary key in the database table
	 * @@GeneratedValue Defines the types of primary key generation strategies
	 * @Column annotation mapped with column in the database table
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sid")
	private Long id;

	/**
	 * @NotEmpty annotated the element must not be null nor empty.Mapped with one
	 *           column it validate with rest API
	 */
	@NotEmpty(message = "Name cannot be empty")
	@Column(name = "sname")
	private String name;

	/**
	 * @Min The annotated element must be a number whose value must be higher or
	 *      equal to the specified minimum.
	 * 
	 */
	@Min(value = 1, message = "Age must be a Positive Number")
	@Column(name = "sage")
	private int age;

	/**
	 * @Pattern The annotated CharSequence must match the specified regular
	 *          expression.
	 */
	@Pattern(regexp = "^[A-F]$", message = "Grade must be one of A,B,C,D,E,F")
	@Column(name = "sgrade")
	private String grade;

	@Column(name = "saddress")
	private String address;

	// Constructor for the create empty object
	public Student() {
	}

	// ParamConstructor for the creating object with valid information
	public Student(Long id, String name, int age, String grade, String address) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.address = address;
	}

	// Getters and setters...

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", grade=" + grade + ", address=" + address
				+ "]";
	}

}
