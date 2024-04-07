package com.inn.dealership.service;

import com.inn.dealership.pojo.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    ResponseEntity<String> addNewCategory(Map<String, String> resuestMap);

    ResponseEntity<String> updateCategory(Map<String, String> requestMap);

    ResponseEntity<List<Category>> getAllCategories(String filterValue);
}
