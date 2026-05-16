package com.first.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pack.Student;


@Configuration
@ComponentScan("pack")
public class Appconfig {

    @Bean
    public Student studentBean(){
        return new Student();
    }

}
