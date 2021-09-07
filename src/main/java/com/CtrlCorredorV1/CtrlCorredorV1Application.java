package com.CtrlCorredorV1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com" })
@EntityScan(basePackages = { "com.CtrlCorredorV1" })
public class CtrlCorredorV1Application {
	public static void main(String[] args) {
		SpringApplication.run(CtrlCorredorV1Application.class, args);
	}

}
