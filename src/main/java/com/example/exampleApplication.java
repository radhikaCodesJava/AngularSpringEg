package com.example;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.entity.User;
import com.example.repo.UserRepository;

@SpringBootApplication(scanBasePackages = "com.example")
public class exampleApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("in main class: ");
		SpringApplication.run(exampleApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(final UserRepository userRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                User user = new User(name, name.toLowerCase() + "@domain.com");
                userRepository.save(user);
            });
            userRepository.findAll().forEach(System.out::println);
        };
    }

}
