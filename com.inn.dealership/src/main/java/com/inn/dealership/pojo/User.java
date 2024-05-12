/**
 * The {@code User} class represents a user entity with attributes such as ID, name, email, contact number, password,
 * status, and role. This entity is mapped to the "user" table in the database.
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
 * The class contains various named queries defined using {@link NamedQuery} annotations, which are used to define
 * custom JPQL queries for common operations like fetching a user by email ID or user ID, fetching all users
 * with a specific role, updating user status, and deleting a user by ID.
 * </p>
 * <p>
 * The {@code User} class implements {@link Serializable} to indicate that instances of this class can be serialized
 * and deserialized.
 * </p>
 */

package com.inn.dealership.pojo;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;


@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email=:email")
@NamedQuery(name = "User.findByUserId", query = "select u from User u where u.id=:id")
@NamedQuery(name = "User.getAllUsers", query = "select new com.inn.dealership.wrapper.UserWrapper(u.id, u.name, u.email, u.contactNumber, u.status) from User u where u.role='user'")
@NamedQuery(name = "User.updateStatus", query = "update User u set u.status=:status where u.id=:id")
@NamedQuery(name = "User.deleteUserById", query = "delete User u where u.id=:id")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
public class User implements Serializable {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * The name of the user.
     */
    @Column(name ="name")
    private String name;

    /**
     * The contact number of the user.
     */
    @Column(name = "contactNumber")
    private String contactNumber;

    /**
     * The email address of the user (also serves as the username).
     */
    @Column(name = "email")
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = "password")
    private String password;

    /**
     * The status of the user.
     * true means the user is an admin.
     */
    @Column(name = "status")
    private String status;

    /**
     * The role of the user.
     */
    @Column(name = "role")
    private String role;


}
