package com.substring.foodie.substring_foodie.Example;

import com.substring.foodie.substring_foodie.payload.UserData;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

//    @RequestMapping(value = "/login" , method = RequestMethod.POST  )
    @GetMapping("/login")
    public Map<String , Object>login(@RequestBody Map<String,Object>data , @RequestHeader("User-Agent") String agent){

        String test = null;
        test.length();//null pointer exception ko generate kiaa hai


        logger.info("login request : {}" , data);
        logger.info(" agent : {}" , agent);
        return data;
    }


    @RequestMapping("/signup")
    public String signup(@Valid @RequestBody  UserData userData){
        logger.info("user name:{}" , userData.getName());
        logger.info("age :{}" , userData.getAge());
        logger.info("password :{}" , userData.getPassword());
        logger.info("password :{}" , userData.getEmail());
        logger.info("gender :{}" , userData.getGender());
//        logger.info("name:{}" , Data.get(0));
        return "we got data";
    }


    //exception handling method:for this controller
//    @ExceptionHandler(NullPointerException.class)
//    public String handleNullPointerException(NullPointerException ex){
//        logger.error(ex.getMessage());
//        ex.printStackTrace();
//        return ex.getMessage();
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
//        logger.error(ex.getMessage());
//        ex.printStackTrace();
//        return ex.getMessage();
//    }

}
