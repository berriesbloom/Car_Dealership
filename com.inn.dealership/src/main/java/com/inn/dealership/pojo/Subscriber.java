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

    public static final long serialVersionUID = 112344L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
