/**
 * Provides a set of data access methods for performing CRUD operations and retrieving car-related data.
 * <p>
 * The {@code CarDao} interface extends {@link JpaRepository} to inherit basic CRUD operations
 * </p>
 * <p>
 * Methods annotated with {@link Modifying} and {@link Transactional} are intended for operations that
 * modify the data, such as updating or deleting records. These methods should be executed within a transaction.
 * </p>
 * <p>
 * The methods primarily deal with {@link Car} entities and {@link CarWrapper} wrappers, which are used to represent
 * car data in different formats or structures.
 * </p>
 */

package com.inn.dealership.dao;

import com.inn.dealership.pojo.Car;
import com.inn.dealership.pojo.User;
import com.inn.dealership.wrapper.CarWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarDao extends JpaRepository<Car, Integer> {

    /**
     * Retrieves a list of all cars along with additional data wrapped in {@link CarWrapper}.
     *
     * @return a list of {@link CarWrapper} containing all cars
     */
    List<CarWrapper> getAllCars();

    /**
     * Finds a car by its ID.
     *
     * @param id the ID of the car to find
     * @return the {@link Car} object with the specified ID, or {@code null} if not found
     */
    Car findByCarId(@Param("id") Integer id);

    /**
     * Deletes a car by its ID.
     *
     * @param id the ID of the car to delete
     */
    @Modifying
    @Transactional
    void deleteCarById(@Param("id") Integer id);


    /**
     * Updates the details of a car with the specified ID.
     *
     * @param id       the ID of the car to update
     * @param make     the make of the car
     * @param model    the model of the car
     * @param year     the year of the car
     * @param price    the price of the car
     * @param capacity the capacity of the car
     * @param power    the power of the car
     */
    @Modifying
    @Transactional
    void updateCar(@Param("id") Integer id,
                   @Param("make") String make,
                   @Param("model") String model,
                   @Param("year") Integer year,
                   @Param("price") Integer price,
                   @Param("capacity") Integer capacity,
                   @Param("power") Integer power);


    /**
     * Retrieves a list of cars filtered by category along with additional data wrapped in {@link CarWrapper}.
     *
     * @param id the ID of the category to filter by
     * @return a list of {@link CarWrapper} containing cars filtered by category
     */
    List<CarWrapper> getCarByCategory(@Param("id") Integer id);

    /**
     * Updates the status of a car with the specified ID.
     * This way, we can control the availability of a car.
     * @param status the new status of the car
     * @param id     the ID of the car to update
     */
    @Modifying
    @Transactional
    void updateCarStatus(@Param("status") String status, @Param("id") Integer id);

    /**
     * Retrieves a {@link CarWrapper} containing detailed information of a car by its ID.
     *
     * @param id the ID of the car to retrieve
     * @return a {@link CarWrapper} containing detailed information of the car, or {@code null} if not found
     */
    CarWrapper getCarById(@Param("id") Integer id);

}
