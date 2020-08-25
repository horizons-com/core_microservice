package com.example.demo;

import com.example.demo.Controllers.ApplicationController;
import com.example.demo.Controllers.HomeController;
import com.example.demo.Services.MessageService;
import com.example.demo.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {
	public static final String MESSAGES_SERVICE_URL = "http://MESSAGES-MICROSERVICE";
	public static final String STUDENTS_SERVICE_URL = "http://STUDENTS-MICROSERVICE";

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public StudentService studentService(){
		return new StudentService(STUDENTS_SERVICE_URL);
	}

	@Bean
	public MessageService messageService(){
		return new MessageService(MESSAGES_SERVICE_URL);
	}
}
