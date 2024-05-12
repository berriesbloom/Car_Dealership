/**
 * The {@code Category} class represents a category entity with attributes such as ID and name. This entity
 * is mapped to the "category" table in the database.
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
 * JPQL query for fetching all categories that have associated cars with status 'true'.
 * </p>
 * <p>
 * The {@code Category} class implements {@link Serializable} to indicate that instances of this class can be serialized
 * and deserialized.
 * </p>
 */

package com.inn.dealership.pojo;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;


@NamedQuery(name="Category.getAllCategories", query = "select c from Category c where c.id in (select p.category.id from Car p where p.status='true')")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "category")
public class Category implements Serializable {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * The name of the category.
     */
    @Column(name = "name")
    private String name;

}
