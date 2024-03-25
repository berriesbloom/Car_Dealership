package com.inn.dealership.dao;

import com.inn.dealership.pojo.User;
import com.inn.dealership.wrapper.UserWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The UserDao class is used to inherit method declarations for CRUD operations on on entities of type User. I
 * Integer specifies the type of primary key of User
 *
 */
public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmailId(@Param("email") String email);

    List<UserWrapper> getAllUsers();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

    @Modifying
    @Transactional
    void deleteUserById(@Param("id")Integer id);


}
