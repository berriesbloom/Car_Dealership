package com.inn.dealership;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.dao.CarDao;
import com.inn.dealership.pojo.Car;
import com.inn.dealership.service.CarService;
import com.inn.dealership.service.SubsriberService;
import com.inn.dealership.serviceImplementation.CarServiceImpl;
import com.inn.dealership.serviceImplementation.SubscriberServiceImpl;
import com.inn.dealership.wrapper.CarWrapper;
import org.aspectj.lang.annotation.Before;
import org.hibernate.annotations.AttributeAccessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CarServiceTests {

    @Mock
    private CarDao carDao;

    @Mock
    private SubsriberService subscriberService;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllCars(){
        when(carDao.getAllCars()).thenReturn(new ArrayList<>());

        ResponseEntity<List<CarWrapper>> responseEntity = carService.getAllCars();

        verify(carDao, times(1)).getAllCars();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size());
    }

    @Test
    void testAddNewCarSucces(){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("make", "Toyota");
        requestMap.put("model", "Camry");
        requestMap.put("year", "2022");
        requestMap.put("price", "25000");
        requestMap.put("capacity", "5");
        requestMap.put("power", "200");
        requestMap.put("categoryId", "1");

        Car car = new Car();
        car.setId(1);
        car.setMake("Toyota");
        car.setModel("Camry");
        car.setYear(2022);
        car.setPrice(25000);
        car.setCapacity(5);
        car.setPower(200);

        when(carDao.save(any())).thenReturn(car);

        ResponseEntity<String> response = carService.addNewCar(requestMap);

        verify(carDao, times(1)).save(any());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Car added succesfully", response.getBody());
    }

    @Test
    void testAddNewCarFail(){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("model", "Camry");
        requestMap.put("year", "2022");
        requestMap.put("price", "25000");
        requestMap.put("capacity", "5");
        requestMap.put("power", "200");
        requestMap.put("categoryId", "1");

        ResponseEntity<String> response = carService.addNewCar(requestMap);

        verify(carDao, never()).save(any());

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(Constants.UNAUTHORIZED_ACCESS, response.getBody());
    }

    @Test
    void testDeleteCarSucces(){
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "1");

        Car car = new Car();
        Optional<Car> optionalCar = Optional.of(car);

        when(carDao.findById(1)).thenReturn(optionalCar);

        ResponseEntity<String> response = carService.deleteCar(requestMap);

        verify(carDao, times(1)).findById(1);

        verify(carDao, times(1)).deleteCarById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Car deleted succesfully!", response.getBody());
    }

    @Test
    void testDeleteCarFail(){
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "1");

        Optional<Car> optionalCar = Optional.empty();

        when(carDao.findById(1)).thenReturn(optionalCar);

        ResponseEntity<String> response = carService.deleteCar(requestMap);

        verify(carDao, times(1)).findById(1);

        verify(carDao, never()).deleteCarById(anyInt());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Car id does not exist", response.getBody());
    }

    @Test
    void testUpdateCarSuccess(){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "1");
        requestMap.put("make", "Toyota");
        requestMap.put("model", "Camry");
        requestMap.put("year", "2022");
        requestMap.put("price", "25000");
        requestMap.put("capacity", "5");
        requestMap.put("power", "200");

        Car car = new Car();
        Optional<Car> optionalCar = Optional.of(car);

        when(carDao.findById(1)).thenReturn(optionalCar);

        ResponseEntity<String> response = carService.update(requestMap);

        verify(carDao, times(1)).findById(1);

        verify(carDao, times(1)).updateCar(
                eq(1),
                eq("Toyota"),
                eq("Camry"),
                eq(2022),
                eq(25000),
                eq(5),
                eq(200)
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Car updated successfully!", response.getBody());
    }

    @Test
    void testUpdateCarFail(){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "1");

        Optional<Car> optionalCar = Optional.empty();

        when(carDao.findById(1)).thenReturn(optionalCar);

        ResponseEntity<String> response = carService.update(requestMap);

        verify(carDao, times(1)).findById(1);

        verify(carDao, never()).updateCar(anyInt(), anyString(), anyString(), anyInt(), anyInt(), anyInt(), anyInt());


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Car id does not exist.", response.getBody());
    }
}
