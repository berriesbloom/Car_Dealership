package com.inn.dealership.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

public interface CategoryService {

    ResponseEntity<String> addNewCategory(Map<String, String> resuestMap);
}
