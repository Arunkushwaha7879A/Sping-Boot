package com.first;

import com.first.controller.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

//@SpringBootApplication =
//@Configuration+
//@ComponentScan("com.first")+
//@EnableAutoConfiguration

//@ComponentScan(basePackages = {"com.first" , "pack"})

public class FirstBootProjectApplication {

	public static void main(String[] args) {

        //bootstraping your spring boot application
		SpringApplication.run(FirstBootProjectApplication.class, args);

//        ConfigurableApplicationContext container = SpringApplication.run(FirstBootProjectApplication.class, args);
//        HomeController controller = container.getBean("homeController" ,HomeController.class);
//        System.out.println(controller);
	}

}
