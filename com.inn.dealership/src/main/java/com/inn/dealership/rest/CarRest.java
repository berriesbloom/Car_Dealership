/**
 * The {@code CarRest} interface defines the REST API endpoints related to car operations.
 * <p>
 * This interface provides methods for retrieving all cars, adding a new car, deleting a car,
 * updating car details, updating car status, retrieving cars by category, and retrieving a car by ID.
 * Each method maps to a specific HTTP request method (GET, POST, PUT, DELETE) and path.
 * </p>
 * <p>
 * The {@link RequestMapping} annotation at the class level specifies the base path "/car" for all endpoints
 * defined in this interface.
 * </p>
 * <p>
 * Each method is annotated with a specific HTTP request method annotation ({@link GetMapping}, {@link PostMapping},
 * {@link PutMapping}, {@link DeleteMapping}) and a path to specify the endpoint's URL pattern.
 * The method parameters and return types define the request and response formats, respectively.
 * </p>
 * <p>
 * The request and response formats typically use {@link Map} to represent JSON objects in the HTTP request body
 * and {@link ResponseEntity} to encapsulate the HTTP response with the appropriate status code and body.
 * The {@link CarWrapper} class is used to wrap car-related data when interacting with the REST API.
 * </p>
 * <p>
 */

package com.inn.dealership.rest;

import com.inn.dealership.wrapper.CarWrapper;
import jdk.jfr.Frequency;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/car")
public interface CarRest {

    /**
     * Retrieves all cars.
     *
     * @return a {@link ResponseEntity} containing a list of {@link CarWrapper} representing all cars,
     *         with an appropriate HTTP status code
     */
    @GetMapping(path = "/get")
    ResponseEntity<List<CarWrapper>> getAllCars();

    /**
     * Adds a new car.
     *
     * @param requestMap a {@link Map} containing car details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @PostMapping(path = "/add")
    ResponseEntity<String> addNewCar(@RequestBody Map<String, String> requestMap);

    /**
     * Deletes a car.
     *
     * @param requestMap a {@link Map} containing car ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @DeleteMapping(path = "/delete")
    ResponseEntity<String> deleteCar(@RequestBody(required = true) Map<String, String> requestMap);

    /**
     * Updates car details.
     *
     * @param requestMap a {@link Map} containing car details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @PutMapping(path = "/update")
    ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);

    /**
     * Updates car status.
     *
     * @param requestMap a {@link Map} containing car status and ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @PutMapping(path = "/updateStatus")
    ResponseEntity<String> updateStatus(@RequestBody Map<String, String> requestMap);

    /**
     * Retrieves cars by category.
     *
     * @param requestMap a {@link Map} containing category ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a list of {@link CarWrapper} representing cars by category,
     *         with an appropriate HTTP status code
     */
    @GetMapping(path = "/getByCategory")
    ResponseEntity<List<CarWrapper>> getByCategory(@RequestBody(required = true) Map<String, String> requestMap);

    /**
     * Retrieves a car by ID.
     *
     * @param requestMap a {@link Map} containing car ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a {@link CarWrapper} representing the car by ID,
     *         with an appropriate HTTP status code
     */
    @GetMapping(path = "/getById")
    ResponseEntity<CarWrapper> getCarById(@RequestBody Map<String, String> requestMap);

}
