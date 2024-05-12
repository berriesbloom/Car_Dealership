/**
 * The {@code Subscriber} class represents a subscriber entity that associates a {@link User} with a {@link Car}.
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
 * The class contains a named query defined using {@link NamedQuery} annotation, which is used to define a custom
 * JPQL query for fetching a subscriber by a combination of user ID and car ID.
 * </p>
 * <p>
 * The {@code Subscriber} class implements {@link Serializable} to indicate that instances of this class can be serialized
 * and deserialized.
 * </p>
 */

package com.inn.dealership.pojo;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@NamedQuery(name = "Subscriber.findByUserAndCarId", query = "select s from Subscriber s where s.user.id=:userId and s.car.id=:carId")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "subscriber")
public class Subscriber implements Serializable {

    /**
     * Serial version UID for serialization.
     */
    public static final long serialVersionUID = 112344L;

    /**
     * The unique identifier for the subscriber.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * The user associated with the subscriber.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The car associated with the subscriber.
     */
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
