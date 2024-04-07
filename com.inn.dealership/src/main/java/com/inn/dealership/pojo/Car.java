package com.inn.dealership.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.io.Serializable;


@NamedQuery(name = "Car.getAllCars", query = "select new com.inn.dealership.wrapper.CarWrapper(c.id, c.make, c.model, c.year, c.price, c.capacity, c.power, c.status, c.category.id, c.category.name) from Car c")
@NamedQuery(name = "Car.deleteCarById", query = "delete Car c where c.id=:id")
@NamedQuery(name = "Car.updateCar", query = "update Car c set c.make=COALESCE(:make,c.make), c.model=COALESCE(:model,c.model), c.year=COALESCE(:year,c.year) , c.price=COALESCE(:price,c.price), c.capacity=COALESCE(:capacity,c.capacity), c.power=COALESCE(:power,c.power) where c.id=:id ")
@NamedQuery(name = "Car.updateCarStatus", query = "update Car c set c.status=:status where c.id=:id")
@NamedQuery(name = "Car.getCarByCategory", query = "select new com.inn.dealership.wrapper.CarWrapper(c.id, c.make, c.model) from Car c where c.category.id=:id and c.status='true'")
@NamedQuery(name = "Car.getCarById", query = "select new com.inn.dealership.wrapper.CarWrapper(c.id, c.make, c.model, c.year, c.price, c.capacity, c.power, c.status) from Car c where c.id=:id")


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", nullable = true)
    private Category category;

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

    @Column(name = "status")
    private String status;
}
