package com.inn.dealership.dao;

import com.inn.dealership.pojo.Car;
import com.inn.dealership.pojo.Subscriber;
import com.inn.dealership.pojo.User;
import com.inn.dealership.serviceImplementation.SubscriberServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriberDao extends JpaRepository<Subscriber, Integer> {
    //method to get all subscibers
    List<Subscriber> findByCar(Car car);

    Subscriber findByUserAndCarId(@Param("userId")Integer userId, @Param("carId")Integer carId);

}
