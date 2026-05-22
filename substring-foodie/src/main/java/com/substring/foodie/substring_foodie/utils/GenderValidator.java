package com.substring.foodie.substring_foodie.utils;

import com.substring.foodie.substring_foodie.exception.GlobalExceptionHandler;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;


public class GenderValidator implements ConstraintValidator<ValidGender , String> {

    private Logger logger = Logger.getLogger(this.getClass().getName());


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {


        if (s == null || s.isEmpty()){
            logger.warning("invalid gender string");
            return false;
        }
        if(s.toLowerCase().equals("male") || s.toLowerCase().equals("female")){
            return true;
        }
        return false; 
    }
}
