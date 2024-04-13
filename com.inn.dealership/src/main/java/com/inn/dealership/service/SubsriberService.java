package com.inn.dealership.service;

import com.inn.dealership.pojo.Car;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;

public interface SubsriberService {

    ResponseEntity<String> subscribeCar(Integer userId, Integer carId);

    void notifySubscribers(Car car);
}
