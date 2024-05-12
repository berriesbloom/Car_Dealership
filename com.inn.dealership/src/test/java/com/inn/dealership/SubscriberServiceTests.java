package com.inn.dealership;

import com.inn.dealership.dao.CarDao;
import com.inn.dealership.dao.SubscriberDao;
import com.inn.dealership.dao.UserDao;
import com.inn.dealership.pojo.Car;
import com.inn.dealership.pojo.Subscriber;
import com.inn.dealership.pojo.User;
import com.inn.dealership.serviceImplementation.SubscriberServiceImpl;
import com.inn.dealership.utils.EmailUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SubscriberServiceTests {

    @Mock
    SubscriberDao subscriberDao;

    @Mock
    UserDao userDao;

    @Mock
    CarDao carDao;

    @Mock
    EmailUtil emailUtil;

    @InjectMocks
    SubscriberServiceImpl subscriberService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSubscribeCarSuccess() {
        Integer userId = 1;
        Integer carId = 1;

        User user = new User();
        user.setId(userId);

        Car car = new Car();
        car.setId(carId);

        when(userDao.findByUserId(userId)).thenReturn(user);
        when(carDao.findByCarId(carId)).thenReturn(car);
        when(subscriberDao.findByUserAndCarId(userId, carId)).thenReturn(null);

        ResponseEntity<String> response = subscriberService.subscribeCar(userId, carId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully subscribed!", response.getBody());
        verify(subscriberDao, times(1)).save(any(Subscriber.class));
    }

    @Test
    void testSubscribeCarSubscriberExists() {
        Integer userId = 1;
        Integer carId = 1;

        User user = new User();
        user.setId(userId);

        Car car = new Car();
        car.setId(carId);

        Subscriber existingSubscriber = new Subscriber();
        existingSubscriber.setUser(user);
        existingSubscriber.setCar(car);

        when(userDao.findByUserId(userId)).thenReturn(user);
        when(carDao.findByCarId(carId)).thenReturn(car);
        when(subscriberDao.findByUserAndCarId(userId, carId)).thenReturn(existingSubscriber);

        ResponseEntity<String> response = subscriberService.subscribeCar(userId, carId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Subscriber already exists!", response.getBody());
        verify(subscriberDao, never()).save(any(Subscriber.class));
    }

    @Test
    void testUnsubscribeCarSuccess() {
        Integer userId = 1;
        Integer carId = 1;

        Subscriber existingSubscriber = new Subscriber();

        when(subscriberDao.findByUserAndCarId(userId, carId)).thenReturn(existingSubscriber);

        ResponseEntity<String> response = subscriberService.unsubscribeCar(userId, carId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully unsubscribed!", response.getBody());
        verify(subscriberDao, times(1)).delete(existingSubscriber);
    }

    @Test
    void testUnsubscribeCarSubscriberNotFound() {
        Integer userId = 1;
        Integer carId = 1;

        when(subscriberDao.findByUserAndCarId(userId, carId)).thenReturn(null);

        ResponseEntity<String> response = subscriberService.unsubscribeCar(userId, carId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Subscriber not found!", response.getBody());
        verify(subscriberDao, never()).delete(any(Subscriber.class));
    }

    @Test
    void testNotifySubscribers() {

        Car car = new Car();
        car.setId(1);
        car.setPrice(20000);

        User user1 = new User();
        user1.setId(1);
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setId(2);
        user2.setEmail("user2@example.com");

        Subscriber subscriber1 = new Subscriber();
        subscriber1.setId(1);
        subscriber1.setUser(user1);
        subscriber1.setCar(car);

        Subscriber subscriber2 = new Subscriber();
        subscriber2.setId(2);
        subscriber2.setUser(user2);
        subscriber2.setCar(car);

        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(subscriber1);
        subscribers.add(subscriber2);

        when(subscriberDao.findByCar(car)).thenReturn(subscribers);

        subscriberService.notifySubscribers(car);

        verify(subscriberDao, times(1)).findByCar(car);
        verify(emailUtil, times(2)).sendSimpleMessage(anyString(), anyString(), anyString());
    }

}
