/**
 * The {@code UserRest} interface defines the REST API endpoints related to user operations.
 * <p>
 * This interface provides methods for user sign-up, retrieving all users, updating a user, and deleting a user.
 * Each method maps to a specific HTTP request method (GET, POST, PUT, DELETE) and path.
 * </p>
 * <p>
 * The {@link RequestMapping} annotation at the class level specifies the base path "/user" for all endpoints
 * defined in this interface.
 * </p>
 * <p>
 * Each method is annotated with a specific HTTP request method annotation ({@link PostMapping}, {@link GetMapping},
 * {@link PutMapping}, {@link DeleteMapping}) and a path to specify the endpoint's URL pattern.
 * The method parameters and return types define the request and response formats, respectively.
 * </p>
 * <p>
 * The request and response formats typically use {@link Map} to represent JSON objects in the HTTP request body
 * and {@link ResponseEntity} to encapsulate the HTTP response with the appropriate status code and body.
 * The {@link UserWrapper} class is used to wrap user-related data when interacting with the REST API.
 * </p>
 */

package com.inn.dealership.rest;

import com.inn.dealership.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {

    /**
     * Signs up a new user.
     *
     * @param requestMap a {@link Map} containing user details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    /**
     * Retrieves all users.
     *
     * @return a {@link ResponseEntity} containing a list of {@link UserWrapper} representing all users,
     *         with an appropriate HTTP status code
     */
    @GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllUsers();

    /**
     * Updates a user.
     *
     * @param requestMap a {@link Map} containing user details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @PutMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);

    /**
     * Deletes a user.
     *
     * @param requestMap a {@link Map} containing user ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> delete(@RequestBody(required = true) Map<String, String> requestMap);
}
