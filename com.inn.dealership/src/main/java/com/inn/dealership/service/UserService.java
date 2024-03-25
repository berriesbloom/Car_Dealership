package com.inn.dealership.service;

import com.inn.dealership.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<List<UserWrapper>> getAllUsers();

    ResponseEntity<String> update(Map<String, String> requestMap);

    ResponseEntity<String> deleteUser(Map<String, String> requestMap);
}
