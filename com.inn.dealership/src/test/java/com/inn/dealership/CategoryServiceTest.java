package com.inn.dealership;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.dao.CategoryDao;
import com.inn.dealership.pojo.Car;
import com.inn.dealership.pojo.Category;
import com.inn.dealership.serviceImplementation.CategoryServiceImpl;
import com.inn.dealership.wrapper.UserWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    CategoryDao categoryDao;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllCategories(){
        when(categoryDao.getAllCategories()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Category>> responseEntity = categoryService.getAllCategories("value");

        verify(categoryDao, times(1)).getAllCategories();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size());
    }

    @Test
    void testAddNewCategorySucces(){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "1");
        requestMap.put("name", "SUV");

        Category category = new Category();
        category.setId(1);
        category.setName("SUV");

        when(categoryDao.save(any())).thenReturn(category);

        ResponseEntity<String> response = categoryService.addNewCategory(requestMap);

        verify(categoryDao, times(1)).save(any());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category added successfully!", response.getBody());
    }

    @Test
    void testUpdateCategorySuccess(){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "1");
        requestMap.put("name", "SUV");

        Category category = new Category();
        Optional<Category> optionalCategory = Optional.of(category);

        when(categoryDao.findById(1)).thenReturn(optionalCategory);

        ResponseEntity<String> response = categoryService.updateCategory(requestMap);

        verify(categoryDao, times(1)).findById(1);

        verify(categoryDao, times(1)).save(any());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category updates successfully!", response.getBody());
    }

    @Test
    void testUpdateCategoryFail() {

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "1");
        requestMap.put("name", "SUV");

        when(categoryDao.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = categoryService.updateCategory(requestMap);

        verify(categoryDao, times(1)).findById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category id does not exist!", response.getBody());
    }

}
