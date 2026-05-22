package com.substring.foodie.substring_foodie.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {GenderValidator.class})
public @interface ValidGender {
    String message() default "invalid gender";
    Class<?>[] groups() default {};
    //extra metadata
    Class<? extends Payload> [] payload() default {} ;
}
