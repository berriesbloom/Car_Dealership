package com.inn.dealership.serviceImplementation;


import com.inn.dealership.constants.Constants;
import com.inn.dealership.dao.CarDao;
import com.inn.dealership.dao.SubscriberDao;
import com.inn.dealership.dao.UserDao;
import com.inn.dealership.pojo.Car;
import com.inn.dealership.pojo.Subscriber;
import com.inn.dealership.pojo.User;
import com.inn.dealership.service.SubsriberService;
import com.inn.dealership.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The {@code SubscriberServiceImpl} class implements the {@link SubsriberService} interface.
 * It provides the implementation for subscriber-related service operations.
 */
@Service
public class SubscriberServiceImpl implements SubsriberService {

    @Autowired
    SubscriberDao subscriberDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CarDao carDao;

    @Autowired
    EmailUtil emailUtil;

    /**
     * Subscribes a user to receive updates about a car.
     *
     * @param userId the ID of the user
     * @param carId the ID of the car
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> subscribeCar(Integer userId, Integer carId) {
        try{
            Subscriber existingSub = subscriberDao.findByUserAndCarId(userId, carId);
            if(existingSub != null){
                return new ResponseEntity<>("Subscriber already exists!", HttpStatus.BAD_REQUEST);
            }

            User user = userDao.findByUserId(userId);
            Car car = carDao.findByCarId(carId);

            Subscriber subscriber = new Subscriber();
            subscriber.setUser(user);
            subscriber.setCar(car);

            subscriberDao.save(subscriber);

            return new ResponseEntity<>("Successfully subscribed!", HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }

    /**
     * Unsubscribes a user from receiving updates about a car.
     *
     * @param userId the ID of the user
     * @param carId the ID of the car
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> unsubscribeCar(Integer userId, Integer carId) {
        try{
            Subscriber subscriber = subscriberDao.findByUserAndCarId(userId, carId);
            if (subscriber == null) {
                return new ResponseEntity<>("Subscriber not found!", HttpStatus.NOT_FOUND);
            }
            subscriberDao.delete(subscriber);
            return new ResponseEntity<>("Successfully unsubscribed!", HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Updates a subscriber with information about a car.
     *
     * @param subscriber the subscriber to update
     * @param car the car with updated information
     */
    @Override
    public void update(Subscriber subscriber, Car car) {
        try{
            String userEmail = subscriber.getUser().getEmail();
            emailUtil.sendSimpleMessage(userEmail, "Car price updated!", "The " + car.getMake() + " " + car.getModel() +" just got a discount! Check it out!");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Notifies all subscribers of a car about updates.
     *
     * @param car the car for which subscribers need to be notified
     */
    public void notifySubscribers(Car car){
        try{
            List<Subscriber> subscribers = subscriberDao.findByCar(car);

            for(Subscriber subscriber1: subscribers){
                update(subscriber1, car);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
