package com.substring.foodie.substring_foodie.config;

public class AppConstants {
    public static final String ROLE_ADMIN="ADMIN";
    public static final String ROLE_GUEST="GUEST";

    public static String getRoleGuest(){
        return "ROLE_"+ROLE_GUEST;
    }

    public static String getRoleAdmin(){
        return "ROLE_"+ROLE_ADMIN;
    }

}
