/**
 * Provides a set of data access methods for performing CRUD operations and retrieving category-related data.
 * <p>
 * The {@code CategoryDao} interface extends {@link JpaRepository} to inherit basic CRUD operations and adds
 * custom query methods tailored for category-related operations on {@link Category} entities.
 * </p>
 * <p>
 * This interface primarily deals with {@link Category} entities, representing categories that can be associated
 * with {@link com.inn.dealership.pojo.Car} entities to categorize them.
 * </p>
 */

package com.inn.dealership.dao;

import com.inn.dealership.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer> {

    /**
     * Retrieves a list of all categories.
     *
     * @return a list of {@link Category} containing all categories
     */
    List<Category> getAllCategories();
}
