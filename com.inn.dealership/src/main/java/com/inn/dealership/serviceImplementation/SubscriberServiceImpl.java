package com.inn.dealership.serviceImplementation;


import com.inn.dealership.dao.CarDao;
import com.inn.dealership.dao.SubscriberDao;
import com.inn.dealership.dao.UserDao;
import com.inn.dealership.pojo.Car;
import com.inn.dealership.pojo.Subscriber;
import com.inn.dealership.pojo.User;
import com.inn.dealership.service.SubsriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriberServiceImpl implements SubsriberService {

    @Autowired
    SubscriberDao subscriberDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CarDao carDao;

    @Override
    public ResponseEntity<String> subscribeCar(Integer userId, Integer carId) {
        User user = userDao.findByUserId(userId);
        Car car = carDao.findByCarId(carId);

        Subscriber subscriber = new Subscriber();
        subscriber.setUser(user);
        subscriber.setCar(car);

        subscriberDao.save(subscriber);

        return new ResponseEntity<>("Successfully subscribed!", HttpStatus.OK);

    }
    public void notifySubscribers(Car car){
        List<Subscriber> subscribers = subscriberDao.findByCar(car);

        for(Subscriber subscriber1: subscribers){
            subscriber1.updatePrice();
        }
    }
}
