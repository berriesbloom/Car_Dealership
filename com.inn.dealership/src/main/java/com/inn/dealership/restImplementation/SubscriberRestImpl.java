package com.inn.dealership.restImplementation;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.rest.SubscriberRest;
import com.inn.dealership.service.SubsriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriberRestImpl implements SubscriberRest {

    @Autowired
    SubsriberService subsriberService;

    @Override
    public ResponseEntity<String> subscribeCar(Integer userId, Integer carId) {
        try{
            subsriberService.subscribeCar(userId, carId);
            return ResponseEntity.ok("Subscribed Successfully!");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> unsubscribeCar(Integer userId, Integer carId) {
        try{
            subsriberService.unsubscribeCar(userId, carId);
            return ResponseEntity.ok("Unsubscribed Successfully!");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
