/**
 * The {@code Car} class represents a car entity with various attributes such as make, model, year, price, capacity,
 * power, status, and category. This entity is mapped to the "car" table in the database.
 * <p>
 * The class is annotated with {@link Entity} to indicate that it is a JPA entity and {@link Data} from Lombok
 * to automatically generate getters, setters, and other utility methods.
 * </p>
 * <p>
 * The {@link DynamicInsert} and {@link DynamicUpdate} annotations are used to enable dynamic SQL generation,
 * which can optimize the SQL statements by inserting only the non-null values during INSERT operations and
 * updating only the changed values during UPDATE operations.
 * </p>
 * <p>
 * The class contains various named queries defined using {@link NamedQuery} annotations, which are used to
 * define custom JPQL queries for common operations like fetching all cars, deleting a car by ID, updating a car,
 * etc.
 * </p>
 * <p>
 * The {@code Car} class implements {@link Serializable} to indicate that instances of this class can be serialized
 * and deserialized.
 * </p>
 */

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
@NamedQuery(name = "Car.findByCarId", query = "select c from Car c where c.id=:id")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "car")
public class Car implements Serializable {

    /**
     * Serial version UID for serialization.
     */
    public static final long serialVersionUID = 1123457L;

    /**
     * The unique identifier for the car.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * The category associated with the car.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", nullable = true)
    private Category category;

    /**
     * The make of the car.
     */
    @Column(name = "make")
    private String make;

    /**
     * The model of the car.
     */
    @Column(name = "model")
    private String model;

    /**
     * The year of manufacture of the car.
     */
    @Column(name = "year")
    private Integer year;

    /**
     * The price of the car.
     */
    @Column(name = "price")
    private Integer price;

    /**
     * The capacity of the car.
     */
    @Column(name = "capacity")
    private Integer capacity;

    /**
     * The power of the car.
     */
    @Column(name = "power")
    private Integer power;

    /**
     * The status of the car.
     */
    @Column(name = "status")
    private String status;
}
