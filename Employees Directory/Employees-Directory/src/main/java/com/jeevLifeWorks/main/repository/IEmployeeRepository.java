package com.jeevLifeWorks.main.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeevLifeWorks.main.entity.Employee;

/**
 * The IEmployeeRepository interface provides methods to interact with the
 * database for Employee entities using Spring Data ..
 */
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "SELECT salary FROM employees WHERE department_id = :department_id ", nativeQuery = true)
	List<Double> findSalaryByDepartmentId(@Param("department_id") int department_id);

	@Query(value = "SELECT * FROM employees WHERE joining_date BETWEEN :startDate And :endDate", nativeQuery = true)
	List<Employee> findByJoiningDateBetween(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	@Query(value = "SELECT experience_years FROM employees WHERE id = :emplId ", nativeQuery = true)
	int findEmployeeExperience(@Param("emplId") Long emplId);

	@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM employees WHERE experience_years > 4 AND performance_score > 90 AND id = :employeeId", nativeQuery = true)
	Long findPromotionEligibility(@Param("employeeId") Long employeeId);

	@Query(value = "SELECT department, AVG(performance_score) AS avg_performance_score FROM employees GROUP BY department ORDER BY avg_performance_score DESC", nativeQuery = true)
	List<Object[]> findDepartmentsRankedByAveragePerformanceScore();

	@Query(value = "SELECT * FROM employees WHERE department_id = :departmentId ", nativeQuery = true)
	List<Employee> findEmployeeByDepartmentId(@Param("departmentId") int departmentId);

	@Query(value = "SELECT email FROM employees ", nativeQuery = true)
	List<String> findByEmail();

	@Query(value = "SELECT * FROM employees WHERE email = :email ", nativeQuery = true)
	List<Employee> findByEmail(String email);

}
