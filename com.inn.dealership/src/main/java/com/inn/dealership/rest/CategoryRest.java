package com.inn.dealership.rest;

import com.inn.dealership.pojo.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/category")
public interface CategoryRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewCategory(@RequestBody(required = true) Map<String, String> requestMap);

    @PutMapping(path = "/update")
    ResponseEntity<String> updateCategoy(@RequestBody(required = true) Map<String, String > requestMap);
    
    @GetMapping(path = "/get")
    ResponseEntity<List<Category>> getAllCategories(@RequestBody(required = false) String filterValue);
}
