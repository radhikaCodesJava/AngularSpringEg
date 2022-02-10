package com.demo.assignment.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.assignment.entity.programEntity;
//import com.demo.assignment.exception.ResourceNotFoundException;
import com.demo.assignment.repo.programRepository;
import com.demo.assignment.service.programService;



@RestController
@RequestMapping
public class programController {
	
	@Autowired
	programRepository progRepo;
	
	@Autowired
	programService progService;
	
	//get list of programs
	@GetMapping(value = "/allPrograms")
	private ResponseEntity<?> getPrograms() //throws ResourceNotFoundException   
	{ 
		System.out.println("in getall programs");
		//List<programEntity> programList= progRepo.findAll();
		List<programEntity> programList = progService.getAllPrograms();
		
		
		return ResponseEntity.ok(programList);  
	}  
	
	
}
