package com.inn.dealership.restImplementation;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.rest.CarRest;
import com.inn.dealership.service.CarService;
import com.inn.dealership.wrapper.CarWrapper;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The {@code CarRestImpl} class implements the {@link CarRest} interface.
 * It defines the REST API endpoints related to car operations.
 */
@RestController
public class CarRestImpl implements CarRest {

    @Autowired
    CarService carService;


    /**
     * Retrieves all cars.
     *
     * @return a {@link ResponseEntity} containing a list of {@link CarWrapper} representing all cars,
     *         with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<List<CarWrapper>> getAllCars() {
        try{
            return carService.getAllCars();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Adds a new car.
     *
     * @param requestMap a {@link Map} containing car details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> addNewCar(Map<String, String> requestMap) {
        try{
            return carService.addNewCar(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Deletes a car.
     *
     * @param requestMap a {@link Map} containing car ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> deleteCar(Map<String, String> requestMap) {
        try{
            return carService.deleteCar(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Updates a car.
     *
     * @param requestMap a {@link Map} containing car details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
            return carService.update(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Updates the status of a car.
     *
     * @param requestMap a {@link Map} containing car ID and status in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try{
            return carService.updateStatus(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Retrieves cars by category.
     *
     * @param requestMap a {@link Map} containing category ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a list of {@link CarWrapper} representing cars by category,
     *         with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<List<CarWrapper>> getByCategory(Map<String, String> requestMap) {
        try{
            return carService.getByCategory(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Retrieves a car by its ID.
     *
     * @param requestMap a {@link Map} containing car ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a {@link CarWrapper} representing the car with the specified ID,
     *         with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<CarWrapper> getCarById(Map<String, String> requestMap) {
        try{
            return carService.getCarById(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new CarWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
