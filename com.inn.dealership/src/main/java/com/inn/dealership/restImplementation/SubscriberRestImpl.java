package com.inn.dealership.restImplementation;

import com.inn.dealership.rest.SubscriberRest;
import com.inn.dealership.service.SubsriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriberRestImpl implements SubscriberRest {

    @Autowired
    SubsriberService subsriberService;

    @Override
    public ResponseEntity<String> subscribeCar(Integer userId, Integer carId) {
        subsriberService.subscribeCar(userId, carId);
        return ResponseEntity.ok("Subscribed Successfully!");
    }
}
