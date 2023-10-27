package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.repo.UserRepository;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")//, methods= {RequestMethod.GET, RequestMethod.POST}, allowedHeaders = {"Authorization", "Origin"},
	//			exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials", "strict-origin-when-cross-origin"})
public class UserController {
	
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/adduser")
    //public User addUser(@RequestBody User user) {
      // return userRepository.save(user);
    //}
    void addUser(@RequestBody User user) {
        userRepository.save(user);
     }

    @PostMapping("/updateuser")
    void modifyUser(@RequestBody User user) {
        userRepository.save(user);
    }
    
    @PostMapping("/deleteuser")
    void deleteUser(@RequestBody User user) {
        userRepository.delete(user);
    }
	
}
