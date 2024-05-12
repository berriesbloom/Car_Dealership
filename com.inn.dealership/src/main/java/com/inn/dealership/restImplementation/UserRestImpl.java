package com.inn.dealership.restImplementation;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.rest.UserRest;
import com.inn.dealership.service.UserService;
import com.inn.dealership.utils.Utils;
import com.inn.dealership.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The {@code UserRestImpl} class implements the {@link UserRest} interface.
 * It defines the REST API endpoints related to user operations.
 */
@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    /**
     * Signs up a new user.
     *
     * @param requestMap a {@link Map} containing user details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Retrieves all users.
     *
     * @return a {@link ResponseEntity} containing a list of {@link UserWrapper} representing all users,
     *         with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try{
            return userService.getAllUsers();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Updates a user.
     *
     * @param requestMap a {@link Map} containing user details in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
            return userService.update(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Deletes a user.
     *
     * @param requestMap a {@link Map} containing user ID in key-value format from the request body
     * @return a {@link ResponseEntity} containing a status message with an appropriate HTTP status code
     */
    @Override
    public ResponseEntity<String> delete(Map<String, String> requestMap) {
        try{
            return userService.deleteUser(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
