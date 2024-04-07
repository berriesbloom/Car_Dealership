package com.inn.dealership.rest;

import com.inn.dealership.wrapper.CarWrapper;
import jdk.jfr.Frequency;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/car")
public interface CarRest {

    @GetMapping(path = "/get")
    ResponseEntity<List<CarWrapper>> getAllCars();

    @PostMapping(path = "/add")
    ResponseEntity<String> addNewCar(@RequestBody Map<String, String> requestMap);

    @DeleteMapping(path = "/delete")
    ResponseEntity<String> deleteCar(@RequestBody(required = true) Map<String, String> requestMap);

    @PutMapping(path = "/update")
    ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);

}
