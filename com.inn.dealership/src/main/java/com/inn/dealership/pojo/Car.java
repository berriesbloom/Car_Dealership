package com.inn.dealership.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;


@NamedQuery(name = "Car.getAllCars", query = "select new com.inn.dealership.wrapper.CarWrapper(c.id, c.make, c.model, c.year, c.price, c.capacity, c.power) from Car c")
@NamedQuery(name = "Car.deleteCarById", query = "delete Car c where c.id=:id")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "car")
public class Car implements Serializable {

    public static final long serialVersionUID = 1123457L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "price")
    private Integer price;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "power")
    private Integer power;
}
