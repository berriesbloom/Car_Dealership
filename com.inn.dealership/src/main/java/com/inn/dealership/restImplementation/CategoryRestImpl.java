package com.inn.dealership.restImplementation;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.pojo.Category;
import com.inn.dealership.rest.CategoryRest;
import com.inn.dealership.service.CategoryService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The {@code CategoryRestImpl} class implements the {@link CategoryRest} interface.
 * It defines the REST API endpoints related to category operations.
 */
@RestController
public class CategoryRestImpl implements CategoryRest {

    @Autowired
    CategoryService categoryService;



    /**
     * Adds a new category.
     *
     * @param requestMap a {@link Map} containing category details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try {
            return categoryService.addNewCategory(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Updates a category.
     *
     * @param requestMap a {@link Map} containing category details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> updateCategoy(Map<String, String> requestMap) {
        try{
            return categoryService.updateCategory(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Retrieves all categories.
     *
     * @param filterValue a filter value to apply to the category list (optional)
     * @return a {@link ResponseEntity} containing a list of {@link Category} representing all categories,
     *         with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<List<Category>> getAllCategories(String filterValue) {
        try{
            return categoryService.getAllCategories(filterValue);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
