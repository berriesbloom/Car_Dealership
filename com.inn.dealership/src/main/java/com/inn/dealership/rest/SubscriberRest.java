package com.inn.dealership.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/subscriber")
public interface SubscriberRest {

    @PostMapping(path = "/subscribe")
    ResponseEntity<String> subscribeCar(@RequestParam Integer userId, @RequestParam Integer carId);

    @DeleteMapping(path = "/delete")
    ResponseEntity<String> unsubscribeCar(@RequestParam Integer userId, @RequestParam Integer carId);
}
