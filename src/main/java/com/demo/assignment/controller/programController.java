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



@RestController
@RequestMapping
public class programController {
	
	@Autowired
	programRepository programRepo;
	
	//get list of programs
	@GetMapping(value = "/allPrograms")
	private ResponseEntity<?> getPrograms() //throws ResourceNotFoundException   
	{ 
		System.out.println("in getall programs");
		List<programEntity> programList= programRepo.findAll();
		
		
		return ResponseEntity.ok(programList);  
	}  
	
	//get program using programId
	@GetMapping(value = "/programs/{programId}", produces = "application/json") 
	private ResponseEntity <?> getOneProgram(@PathVariable("programId") @NotBlank @Positive Integer programId) //throws ResourceNotFoundException
	{  
		System.out.println("in get by programId");
	return ResponseEntity.ok().body(programRepo.findById(programId).get());
		
	}  
	
	//creating post  that saves the program detail in the database
	@PostMapping(path="/program",consumes = "application/json", produces = "application/json")
		public ResponseEntity <?> createAndSaveProgram(programEntity program) //throws  ProgramAlreadyExistsException
		{
		
		System.out.println("in save new program");
			 programEntity saveEntity= programRepo.save(program);
			 
				return ResponseEntity.ok(saveEntity); 
			
		}
	
	//creating put mapping that updates the program detail by programId  
			@PutMapping(path="/programs/{programId}", consumes = "application/json", produces = "application/json")  
			@ResponseBody
			private ResponseEntity <?> updateProgramById(@PathVariable("programId")@NotBlank @Positive Integer programId ,@Valid @RequestBody programEntity modifyProgram) //throws ProgramAlreadyExistsException  
			{  
				System.out.println("in update program");
			return ResponseEntity.ok(programRepo.save(modifyProgram));
			} 
			
			//creating a delete mapping that deletes a specified program  
			@DeleteMapping(path="/programs/{programId}", consumes = "application/json",produces = "application/json")  
			@ResponseBody
			private ResponseEntity<?>  deleteByProgramId(@PathVariable("programId")@NotBlank @Positive Integer programId)   
			{  
				System.out.println("in delete program");
				programRepo.deleteById(programId);
					
				List<programEntity> programList= programRepo.findAll();
				return ResponseEntity.ok(programList);
				
			
			}  
}
