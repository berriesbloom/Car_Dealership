package com.inn.dealership.dao;

import com.inn.dealership.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * The UserDao class is used to inherit method declarations for CRUD operations on on entities of type User. I
 * Integer specifies the type of primary key of User
 *
 */
public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmailId(@Param("email") String email);
}
