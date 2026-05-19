package com.mvc.example.spring_mvc_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringMvcExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcExampleApplication.class, args);
	}

}
