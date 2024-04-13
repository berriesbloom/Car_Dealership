package com.inn.dealership.pojo;


import jakarta.persistence.*;
import lombok.Data;
import observer.Observer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "subscriber")
public class Subscriber implements Serializable, Observer {

    public static final long serialVersionUID = 112344L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Stores the foreign key of the user in the "user_id" column.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;


    @Override
    public void updatePrice() {
        //Notification logic here
        //Call method to send email here
        System.out.println("Notification sent to " + user.getEmail() + " about car update.");
    }
}
