package com.demo.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = "com.demo.assignment")
public class ProgramBatchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgramBatchDemoApplication.class, args);
	}

}
