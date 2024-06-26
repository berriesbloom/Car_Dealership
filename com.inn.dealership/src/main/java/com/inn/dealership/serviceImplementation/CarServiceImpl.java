package com.inn.dealership.serviceImplementation;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inn.dealership.constants.Constants;
import com.inn.dealership.dao.CarDao;
import com.inn.dealership.pojo.Car;
import com.inn.dealership.pojo.Category;
import com.inn.dealership.service.CarService;
import com.inn.dealership.service.SubsriberService;
import com.inn.dealership.utils.Utils;
import com.inn.dealership.wrapper.CarWrapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The {@code CarServiceImpl} class implements the {@link CarService} interface.
 * It provides the implementation for car-related service operations.
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarDao carDao;

    @Autowired
    SubsriberService subsriberService;

    /**
     * Retrieves all cars.
     *
     * @return a {@link ResponseEntity} containing a list of {@link CarWrapper} representing all cars,
     *         with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<List<CarWrapper>> getAllCars() {
        try{
            return new ResponseEntity<>(carDao.getAllCars(), HttpStatus.OK);
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
        //Aici verifica ca request ul sa fie facut de un admin
        try{
            if(validateCarMap(requestMap, false)){
                carDao.save(getCarFromMap(requestMap, false));
                return new ResponseEntity<>("Car added succesfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Helper method to validate car details
    private boolean validateCarMap(Map<String, String> requestMap, boolean validateId){
        if(requestMap.containsKey("make") && requestMap.containsKey("model")){
            if(requestMap.containsKey("id") && validateId){
                return true;
            }else if(!validateId){
                return true;
            }
        }
        return false;
    }

    // Helper method to create a Car object from request map
    private Car getCarFromMap(Map<String, String> requestMap, boolean isAdded){
        Category category =new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));

        Car car = new Car();
        if(isAdded){
            car.setId(Integer.parseInt(requestMap.get("id")));
        }else{
            car.setStatus("true");
        }

        car.setCategory(category);
        car.setMake(requestMap.get("make"));
        car.setModel(requestMap.get("model"));
        car.setYear(Integer.parseInt(requestMap.get("year")));
        car.setPrice(Integer.parseInt(requestMap.get("price")));
        car.setCapacity(Integer.parseInt(requestMap.get("capacity")));
        car.setPower(Integer.parseInt(requestMap.get("power")));
        return car;
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
            Optional<Car> optional = carDao.findById(Integer.parseInt(requestMap.get("id")));
            if(!optional.isEmpty()){
                carDao.deleteCarById(Integer.parseInt(requestMap.get("id")));
                return new ResponseEntity<>("Car deleted succesfully!", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Car id does not exist", HttpStatus.OK);
            }
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
            Optional<Car> optional = carDao.findById(Integer.parseInt(requestMap.get("id")));
            Car car = carDao.findByCarId(Integer.parseInt(requestMap.get("id")));
            if(!optional.isEmpty()){

                String make = requestMap.get("make");
                String model = requestMap.get("model");
                Integer price = null;
                Integer year = null;
                Integer capacity = null;
                Integer power = null;

                if(requestMap.get("year") != null && !requestMap.get("year").isEmpty()){
                    year = Integer.parseInt(requestMap.get("year"));
                }

                if(requestMap.get("price") != null && !requestMap.get("price").isEmpty()){
                    price = Integer.parseInt(requestMap.get("price"));
                }

                if(requestMap.get("capacity") != null && !requestMap.get("capacity").isEmpty()){
                    capacity = Integer.parseInt(requestMap.get("capacity"));
                }

                if(requestMap.get("power") != null && !requestMap.get("power").isEmpty()){
                    power = Integer.parseInt(requestMap.get("power"));
                }

                carDao.updateCar(Integer.parseInt(requestMap.get("id")), make, model, year, price, capacity, power);
                subsriberService.notifySubscribers(car);


                return new ResponseEntity<>("Car updated successfully!", HttpStatus.OK);

            }else {
                return new ResponseEntity<>("Car id does not exist.", HttpStatus.OK);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Updates the status of a car.
     *
     * @param reqeustMap a {@link Map} containing car ID and status in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> reqeustMap) {
        try{
            Optional optional = carDao.findById(Integer.parseInt(reqeustMap.get("id")));
            if(optional.isPresent()){
                carDao.updateCarStatus(reqeustMap.get("status"), Integer.parseInt(reqeustMap.get("id")));
                return Utils.getResponseEntity("Car status updated successfully!", HttpStatus.OK);
            }
            return Utils.getResponseEntity("Car id does not exist!", HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Retrieves cars by category.
     *
     * @param requestMap a {@link Map} containing category ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a list of {@link CarWrapper} representing cars in the specified category,
     *         with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<List<CarWrapper>> getByCategory(Map<String, String> requestMap) {
        try{
            return new ResponseEntity<>(carDao.getCarByCategory(Integer.parseInt(requestMap.get("id"))), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Retrieves a car by ID.
     *
     * @param requestMap a {@link Map} containing car ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a {@link CarWrapper} representing the car with the specified ID,
     *         with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<CarWrapper> getCarById(Map<String, String> requestMap) {
        try{
            return new ResponseEntity<>(carDao.getCarById(Integer.parseInt(requestMap.get("id"))), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new CarWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
