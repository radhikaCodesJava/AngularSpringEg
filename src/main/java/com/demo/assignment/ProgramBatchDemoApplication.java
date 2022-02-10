package com.demo.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = "com.demo.assignment.util")
@SpringBootApplication(scanBasePackages = "com.demo.assignment")
public class ProgramBatchDemoApplication {

	public static void main(String[] args) {
		System.out.println("in main method");
		SpringApplication.run(ProgramBatchDemoApplication.class, args);
	}

}
