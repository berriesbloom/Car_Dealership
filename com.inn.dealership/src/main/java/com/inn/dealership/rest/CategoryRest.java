/**
 * The {@code CategoryRest} interface defines the REST API endpoints related to category operations.
 * <p>
 * This interface provides methods for adding a new category, updating a category, and retrieving all categories.
 * Each method maps to a specific HTTP request method (GET, POST, PUT) and path.
 * </p>
 * <p>
 * The {@link RequestMapping} annotation at the class level specifies the base path "/category" for all endpoints
 * defined in this interface.
 * </p>
 * <p>
 * Each method is annotated with a specific HTTP request method annotation ({@link PostMapping}, {@link PutMapping},
 * {@link GetMapping}) and a path to specify the endpoint's URL pattern.
 * The method parameters and return types define the request and response formats, respectively.
 * </p>
 * <p>
 * The request and response formats typically use {@link Map} to represent JSON objects in the HTTP request body
 * and {@link ResponseEntity} to encapsulate the HTTP response with the appropriate status code and body.
 * The {@link Category} class is used to represent category-related data when interacting with the REST API.
 * </p>
 * <p>
 */

package com.inn.dealership.rest;

import com.inn.dealership.pojo.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/category")
public interface CategoryRest {

    /**
     * Adds a new category.
     *
     * @param requestMap a {@link Map} containing category details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewCategory(@RequestBody(required = true) Map<String, String> requestMap);

    /**
     * Updates a category.
     *
     * @param requestMap a {@link Map} containing category details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @PutMapping(path = "/update")
    ResponseEntity<String> updateCategoy(@RequestBody(required = true) Map<String, String > requestMap);

    /**
     * Retrieves all categories.
     *
     * @param filterValue an optional filter value to apply when retrieving categories
     * @return a {@link ResponseEntity} containing a list of {@link Category} representing all categories,
     *         with an appropriate HTTP status code
     */
    @GetMapping(path = "/get")
    ResponseEntity<List<Category>> getAllCategories(@RequestBody(required = false) String filterValue);
}
