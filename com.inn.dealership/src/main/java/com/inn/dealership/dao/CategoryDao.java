package com.inn.dealership.dao;

import com.inn.dealership.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer> {

    //List<Category> getAllCategories();
}
