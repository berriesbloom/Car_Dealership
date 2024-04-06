package com.inn.dealership.dao;

import com.inn.dealership.pojo.Car;
import com.inn.dealership.wrapper.CarWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarDao extends JpaRepository<Car, Integer> {

    List<CarWrapper> getAllCars();

    @Modifying
    @Transactional
    void deleteCarById(@Param("id") Integer id);

}
