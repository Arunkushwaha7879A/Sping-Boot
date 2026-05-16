package org.core.concepts;

import org.core.collage.Teacher;
import org.core.qualifier.ColdDrink;
import org.core.qualifier.Pepsi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
//@ComponentScan({"org.core.concepts" , "org.core.collage"})
@ComponentScan({"org.core"})
public class ConfigClass {

    @Bean(name="teacher")
    public Teacher getTeacher() { //ispe hamara control nahi tha aur iska hume bean banana tha
        // aur ye method return kar rha hai usko bean bna dega , this is also way to create bean
        return new Teacher();
    }

    @Bean(name = "pepsi2")
    public ColdDrink getColdDrink(){
        return new Pepsi();
    }

}
