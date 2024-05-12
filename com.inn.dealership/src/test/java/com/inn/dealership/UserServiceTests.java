package com.inn.dealership;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.dao.UserDao;
import com.inn.dealership.pojo.Car;
import com.inn.dealership.pojo.User;
import com.inn.dealership.serviceImplementation.UserServiceImpl;
import com.inn.dealership.utils.Utils;
import com.inn.dealership.wrapper.CarWrapper;
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

public class UserServiceTests {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers(){
        when(userDao.getAllUsers()).thenReturn(new ArrayList<>());

        ResponseEntity<List<UserWrapper>> responseEntity = userService.getAllUsers();

        verify(userDao, times(1)).getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size());
    }

    @Test
    void testSignUpSucces(){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("name", "John");
        requestMap.put("contactNumber", "1234567");
        requestMap.put("email", "John@gmail.com");
        requestMap.put("password", "dummypass");
        requestMap.put("status", "false");
        requestMap.put("role", "user");

        User user = new User();
        user.setId(1);
        user.setName("John");
        user.setContactNumber("1234567");
        user.setEmail("John@gmail.com");
        user.setPassword("dummypass");
        user.setStatus("false");
        user.setRole("user");

        when(userDao.save(any())).thenReturn(user);

        ResponseEntity<String> response = userService.signUp(requestMap);

        verify(userDao, times(1)).save(any());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\":\""+"Succesfulyy Registered."+"\"}", response.getBody());
    }

    @Test
    void testSignUpFail(){
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("name", "John");
        requestMap.put("contactNumber", "1234567");
        requestMap.put("email", "John@gmail.com");
        requestMap.put("password", "dummypass");
        requestMap.put("status", "false");
        requestMap.put("role", "user");

        when(userDao.findByEmailId("John@gmail.com")).thenReturn(new User());

        ResponseEntity<String> response = userService.signUp(requestMap);

        verify(userDao, never()).save(any());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"message\":\""+"Email already exists!"+"\"}", response.getBody());
    }

    @Test
    void testDeleteUserSucces(){
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "1");

        User user = new User();
        Optional<User> optional = Optional.of(user);

        when(userDao.findById(1)).thenReturn(optional);

        ResponseEntity<String> response = userService.deleteUser(requestMap);

        verify(userDao, times(1)).findById(1);

        verify(userDao, times(1)).deleteUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\":\""+"User deleted succesfully!"+"\"}", response.getBody());


    }

    @Test
    void testDeleteCarFail(){
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "1");

        Optional<User> optional = Optional.empty();

        when(userDao.findById(1)).thenReturn(optional);

        ResponseEntity<String> response = userService.deleteUser(requestMap);

        verify(userDao, times(1)).findById(1);

        verify(userDao, never()).deleteUserById(anyInt());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\":\""+"User id does not exist,"+"\"}", response.getBody());
    }

    @Test
    void testUpdateUserSuccess(){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id","1");
        requestMap.put("status", "true");

        User user = new User();
        Optional<User> optional = Optional.of(user);

        when(userDao.findById(1)).thenReturn(optional);

        ResponseEntity<String> response = userService.update(requestMap);

        verify(userDao, times(1)).findById(1);

        verify(userDao, times(1)).updateStatus(
                eq("true"),
                eq(1)
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\":\""+"User status updated succesfully!"+"\"}", response.getBody());
    }



    @Test
    void testUpdateCarFail(){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "1");

        Optional<User> optional = Optional.empty();

        when(userDao.findById(1)).thenReturn(optional);

        ResponseEntity<String> response = userService.update(requestMap);

        verify(userDao, times(1)).findById(1);

        verify(userDao, never()).updateStatus(anyString(), anyInt());


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\":\""+"User id does not exist"+"\"}", response.getBody());
    }



}
