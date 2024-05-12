/**
 /**
 * Provides a set of data access methods for performing CRUD operations and retrieving subscriber-related data.
 * <p>
 * The {@code SubscriberDao} interface extends {@link JpaRepository} to inherit basic CRUD operations and adds
 * custom query methods tailored for subscriber-related operations on {@link Subscriber} entities.
 * </p>
 * <p>
 * This interface primarily deals with {@link Subscriber} entities, which represent subscriptions of {@link User}
 * to {@link Car} entities. It provides methods to retrieve subscribers by car and by a combination of user ID and car ID.
 * </p>
 */

package com.inn.dealership.dao;

import com.inn.dealership.pojo.Car;
import com.inn.dealership.pojo.Subscriber;
import com.inn.dealership.pojo.User;
import com.inn.dealership.serviceImplementation.SubscriberServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriberDao extends JpaRepository<Subscriber, Integer> {

    /**
     * Retrieves a list of subscribers associated with a given car.
     *
     * @param car the car for which to retrieve subscribers
     * @return a list of {@link Subscriber} containing subscribers associated with the given car
     */
    List<Subscriber> findByCar(Car car);


    /**
     * Retrieves a subscriber by a combination of user ID and car ID.
     *
     * @param userId the ID of the user
     * @param carId  the ID of the car
     * @return the {@link Subscriber} object associated with the specified user and car IDs,
     *         or {@code null} if not found
     */
    Subscriber findByUserAndCarId(@Param("userId")Integer userId, @Param("carId")Integer carId);

}
