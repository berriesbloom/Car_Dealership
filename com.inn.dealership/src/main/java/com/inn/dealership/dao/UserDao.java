/**
 * Provides a set of data access methods for performing CRUD operations and retrieving user-related data.
 * <p>
 * The {@code UserDao} interface extends {@link JpaRepository} to inherit basic CRUD operations and adds
 * custom query methods tailored for user-related operations on {@link User} entities.
 * </p>
 * <p>
 * This interface primarily deals with {@link User} entities and {@link UserWrapper} wrappers. It provides methods
 * to retrieve users by email ID and user ID, fetch all users along with additional data wrapped in {@link UserWrapper},
 * update user status, and delete users by ID.
 * </p>
 */

package com.inn.dealership.dao;

import com.inn.dealership.pojo.User;
import com.inn.dealership.wrapper.UserWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * Retrieves a user by email ID.
     *
     * @param email the email ID of the user to retrieve
     * @return the {@link User} object with the specified email ID, or {@code null} if not found
     */
    User findByEmailId(@Param("email") String email);

    /**
     * Retrieves a user by user ID.
     *
     * @param id the ID of the user to retrieve
     * @return the {@link User} object with the specified ID, or {@code null} if not found
     */

    User findByUserId(@Param("id") Integer id);

    /**
     * Retrieves a list of all users along with additional data wrapped in {@link UserWrapper}.
     *
     * @return a list of {@link UserWrapper} containing all users
     */
    List<UserWrapper> getAllUsers();

    /**
     * Updates the status of a user with the specified ID.
     *
     * @param status the new status of the user
     * @param id     the ID of the user to update
     * @return the number of affected rows after the update operation
     */
    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     */
    @Modifying
    @Transactional
    void deleteUserById(@Param("id")Integer id);


}
