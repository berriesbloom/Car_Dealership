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

    public CarWrapper(){

    }

    public CarWrapper(Integer id, String make, String model, Integer year, Integer price, Integer capacity, Integer power) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.capacity = capacity;
        this.power = power;
    }
}
