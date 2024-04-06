package com.inn.dealership.service;

import com.inn.dealership.wrapper.CarWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CarService {

    ResponseEntity<List<CarWrapper>> getAllCars();

    ResponseEntity<String> addNewCar(Map<String, String> requestMap);

    ResponseEntity<String> deleteCar(Map<String, String> requestMap);
}
