package com.jeevLifeWorks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jeevLifeWorks.model.Users;

/**
 * Repository interface for managing {@link Users} entities. Extends
 * JpaRepository to provide CRUD operations and custom queries.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	// Checks if a user with the specified username exists.
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Users u WHERE u.username = :username")
	boolean existByUsername(@Param("username") String username);

	// Finds a user by their username.
	@Query("SELECT u FROM Users u WHERE u.username = :username")
	Users findByUsername(@Param("username") String username);

}
