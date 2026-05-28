package com.substring.foodie.substring_foodie.exception;

import com.substring.foodie.substring_foodie.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@RestController
//@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex){
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String , String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
       Map<String , String> erromap = new HashMap<>();
       //fetch all error list from Bindingresult
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        //iterate all the errors and put to map
        allErrors.forEach(error ->{

            //error:we have to fetch the field
            String fieldName = ((FieldError) error).getField();
            String Message = error.getDefaultMessage();

            erromap.put(fieldName,Message);


        });
        return erromap;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException ex){
        ErrorResponse messageOb=ErrorResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).build();
       return new ResponseEntity<>(messageOb,HttpStatus.NOT_FOUND);
    }

}
