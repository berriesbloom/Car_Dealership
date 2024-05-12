package com.inn.dealership.restImplementation;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.rest.SubscriberRest;
import com.inn.dealership.service.SubsriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@code SubscriberRestImpl} class implements the {@link SubscriberRest} interface.
 * It defines the REST API endpoints related to subscriber operations.
 */
@RestController
public class SubscriberRestImpl implements SubscriberRest {

    @Autowired
    SubsriberService subsriberService;

    /**
     * Subscribes a user to a car.
     *
     * @param userId the ID of the user subscribing to the car
     * @param carId the ID of the car to subscribe to
     * @return a {@link ResponseEntity} containing a success message if the subscription was successful,
     *         or an error message with an appropriate HTTP status code otherwise
     */
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

    /**
     * Unsubscribes a user from a car.
     *
     * @param userId the ID of the user unsubscribing from the car
     * @param carId the ID of the car to unsubscribe from
     * @return a {@link ResponseEntity} containing a success message if the unsubscription was successful,
     *         or an error message with an appropriate HTTP status code otherwise
     */
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
