package com.inn.dealership.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "/subscriber")
public interface SubscriberRest {

    @PostMapping(path = "/subscribe")
    ResponseEntity<String> subscribeCar(@RequestParam Integer userId, @RequestParam Integer carId);
}
