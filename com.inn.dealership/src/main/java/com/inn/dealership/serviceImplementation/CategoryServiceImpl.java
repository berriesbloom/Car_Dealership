package com.inn.dealership.serviceImplementation;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.dao.CategoryDao;
import com.inn.dealership.pojo.Category;
import com.inn.dealership.service.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> resuestMap) {
        try{
            if(validateCategoryMap(resuestMap, false)){
                categoryDao.save(getCategoryFromMap(resuestMap, false));
                return new ResponseEntity<>("Category added successfully!", HttpStatus.OK);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId){
        if(requestMap.containsKey("name")){
            if(requestMap.containsKey("id") && validateId){
                return true;
            }else if(!validateId){
                return true;
            }
        }
        return false;
    }

    private Category getCategoryFromMap(Map<String, String> requestMap, boolean isAdded){
        Category category = new Category();
        if(isAdded){
            category.setId(Integer.parseInt(requestMap.get("id")));
        }
        category.setName(requestMap.get("name"));
        return category;
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try{
            if(validateCategoryMap(requestMap, true)){
                Optional optional = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
                if(optional.isPresent()){
                    categoryDao.save(getCategoryFromMap(requestMap, true));
                    return new ResponseEntity<>("Category updates successfully!", HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Category id does not exist!", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories(String filterValue) {
        try{
            if(filterValue != null){
                return new ResponseEntity<List<Category>>(categoryDao.getAllCategories(), HttpStatus.OK);
            }
            return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
