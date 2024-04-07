package com.inn.dealership.serviceImplementation;

import com.inn.dealership.constants.Constants;
import com.inn.dealership.dao.CategoryDao;
import com.inn.dealership.pojo.Category;
import com.inn.dealership.service.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.Map;
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
}
