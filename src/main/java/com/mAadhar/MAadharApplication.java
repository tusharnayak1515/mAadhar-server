package com.mAadhar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mAadhar")
public class MAadharApplication {

	public static void main(String[] args) {
		SpringApplication.run(MAadharApplication.class, args);
	}

}
