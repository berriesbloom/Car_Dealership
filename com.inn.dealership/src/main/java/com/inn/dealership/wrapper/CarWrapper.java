package com.inn.dealership.wrapper;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CarWrapper {

    Integer id;

    String make;

    String model;

    Integer year;

    Integer price;

    Integer capacity;

    Integer power;

    String status;

    Integer categoryId;

    String categoryName;

    public CarWrapper(){

    }

    public CarWrapper(Integer id, String make, String model, Integer year, Integer price, Integer capacity, Integer power, String status) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.capacity = capacity;
        this.power = power;
        this.status = status;
    }

    public CarWrapper(Integer id, String make, String model, Integer year, Integer price, Integer capacity, Integer power, String status, Integer categoryId, String categoryName) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.capacity = capacity;
        this.power = power;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public CarWrapper(Integer id, String make, String model){
        this.id = id;
        this.make = make;
        this.model = model;
    }

    public CarWrapper(String make, String model){
        this.make = make;
        this.model = model;
    }
}
