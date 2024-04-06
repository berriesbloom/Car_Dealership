package com.inn.dealership.serviceImplementation;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inn.dealership.constants.Constants;
import com.inn.dealership.dao.CarDao;
import com.inn.dealership.pojo.Car;
import com.inn.dealership.service.CarService;
import com.inn.dealership.utils.Utils;
import com.inn.dealership.wrapper.CarWrapper;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarDao carDao;

    @Override
    public ResponseEntity<List<CarWrapper>> getAllCars() {
        try{
            return new ResponseEntity<>(carDao.getAllCars(), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addNewCar(Map<String, String> requestMap) {
        //Aici verifica ca request ul sa fie facut de un admin
        try{
            if(validateCarMap(requestMap, false)){
                carDao.save(getCarFromMap(requestMap));
                return new ResponseEntity<>("Car added succesfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

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

    private Car getCarFromMap(Map<String, String> requestMap){
        Car car = new Car();
        car.setMake(requestMap.get("make"));
        car.setModel(requestMap.get("model"));
        car.setYear(Integer.parseInt(requestMap.get("year")));
        car.setPrice(Integer.parseInt(requestMap.get("price")));
        car.setCapacity(Integer.parseInt(requestMap.get("capacity")));
        car.setPower(Integer.parseInt(requestMap.get("power")));
        return car;
    }

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
}
