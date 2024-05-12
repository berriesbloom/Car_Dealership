/**
 * The {@code SubscriberRest} interface defines the REST API endpoints related to subscriber operations.
 * <p>
 * This interface provides methods for subscribing to a car and unsubscribing from a car.
 * Each method maps to a specific HTTP request method (POST, DELETE) and path.
 * </p>
 * <p>
 * The {@link RequestMapping} annotation at the class level specifies the base path "/subscriber" for all endpoints
 * defined in this interface.
 * </p>
 * <p>
 * Each method is annotated with a specific HTTP request method annotation ({@link PostMapping}, {@link DeleteMapping})
 * and a path to specify the endpoint's URL pattern.
 * The method parameters and return types define the request and response formats, respectively.
 * </p>
 * <p>
 * The request and response formats typically use {@link RequestParam} annotations to specify request parameters
 * and {@link ResponseEntity} to encapsulate the HTTP response with the appropriate status code and body.
 * </p>
 */

package com.inn.dealership.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/subscriber")
public interface SubscriberRest {

    /**
     * Subscribes to a car.
     *
     * @param userId the ID of the user subscribing to the car
     * @param carId  the ID of the car to subscribe to
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @PostMapping(path = "/subscribe")
    ResponseEntity<String> subscribeCar(@RequestParam Integer userId, @RequestParam Integer carId);

    /**
     * Unsubscribes from a car.
     *
     * @param userId the ID of the user unsubscribing from the car
     * @param carId  the ID of the car to unsubscribe from
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @DeleteMapping(path = "/delete")
    ResponseEntity<String> unsubscribeCar(@RequestParam Integer userId, @RequestParam Integer carId);
}
