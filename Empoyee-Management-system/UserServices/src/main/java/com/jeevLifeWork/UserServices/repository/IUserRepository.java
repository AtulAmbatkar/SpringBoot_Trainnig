package com.jeevLifeWork.UserServices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeevLifeWork.UserServices.model.User;

/**
 * Repository interface for accessing {@link User} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide standard CRUD
 * operations. It also defines a custom method to find a user by their username.
 * </p>
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

	/**
	 * Retrieves a {@link User} entity by its username.
	 * <p>
	 * This method returns an {@link Optional} containing the user if found, or
	 * {@link Optional#empty()} if not found.
	 * </p>
	 * 
	 * @param username the username of the user to retrieve
	 * @return an {@link Optional} containing the user if found, otherwise empty
	 */
	Optional<User> findByUsername(String username);
}
