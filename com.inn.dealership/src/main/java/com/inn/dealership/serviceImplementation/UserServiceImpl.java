package com.inn.dealership.serviceImplementation;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.dao.UserDao;
import com.inn.dealership.pojo.User;
import com.inn.dealership.service.UserService;
import com.inn.dealership.utils.Utils;
import com.inn.dealership.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * - @Service annotation is used to mark the class as a Spring service that contains business logic and is eligible for dependency injection
 * - @Autowired is used to automatically inject dependencies when needed
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return Utils.getResponseEntity("Succesfulyy Registered.", HttpStatus.OK);
                } else {
                    return Utils.getResponseEntity("Email already exists!", HttpStatus.BAD_REQUEST);
                }
            } else {
                return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateSignUpMap(Map<String, String> requestMap){
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");

        return user;
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try{
            //Implement a method here to check is the request is made by an admin!!!
                return new ResponseEntity<>(userDao.getAllUsers(), HttpStatus.OK);


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
            // Also here: Implement a method to check if the request is made by an admin!!!
                Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
                if(!optional.isEmpty()){
                    userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));

                    return Utils.getResponseEntity("User status updated succesfully!", HttpStatus.OK);
                }else{
                    return Utils.getResponseEntity("User id does not exist", HttpStatus.OK);
                }



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //modifica asta... e ok sau nu?
    @Override
    public ResponseEntity<String> deleteUser(Map<String, String> requestMap) {
        try{
            Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
            if(!optional.isEmpty()){
                // Also here: Implement a method to check if the request is made by an admin!!!
                userDao.deleteUserById(Integer.parseInt(requestMap.get("id")));
                return Utils.getResponseEntity("User deleted succesfully!", HttpStatus.OK);
            }else {
                return Utils.getResponseEntity("User id does not exist,", HttpStatus.OK);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
