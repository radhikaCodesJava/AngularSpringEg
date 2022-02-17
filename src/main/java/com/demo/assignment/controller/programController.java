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

import com.demo.assignment.model.programDTO;
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
		List<programDTO> programList = progService.getAllPrograms();
		
		
		return ResponseEntity.ok(programList);  
	}  
	
	//creating a get mapping that retrieves the detail of a specific program
			@GetMapping(path="programs/{programId}", produces = "application/json")  
			@ResponseBody
			private ResponseEntity <programDTO> getOneProgramById(@PathVariable("programId") @NotBlank @Positive Integer programId)// throws ProgramNotFoundException
			{  
				
			return ResponseEntity.ok().body(progService.getProgramsById(programId));
				
			}  
			
			//creating post mapping that post/creates the program detail in the database  
			@PostMapping(path="/saveprogram",consumes = "application/json", produces = "application/json")  
			@ResponseBody
			private ResponseEntity<?> createAndSaveProgram(@Valid @RequestBody programDTO newProgram)//   throws ProgramAlreadyExistsException
			{  
				programDTO savedProgramedDTO = progService.createAndSaveProgram(newProgram);
				return ResponseEntity.status(HttpStatus.CREATED).body(savedProgramedDTO);  
			 
			} 
				
			//creating put mapping that updates the program detail by programId  
			@PutMapping(path="/putprogram/{programId}", consumes = "application/json", produces = "application/json")  
			@ResponseBody
			private ResponseEntity <programDTO> updateProgramById(@PathVariable("programId")@NotBlank @Positive Integer programId ,@Valid @RequestBody programDTO modifyProgram) //throws ProgramAlreadyExistsException  
			{  
				
			return ResponseEntity.ok(progService.updateProgramById(programId,modifyProgram));
			} 
			
			//creating put mapping that updates the program detail  by programName 
				@PutMapping(path="/program/{programName}", consumes = "application/json", produces = "application/json")  
				@ResponseBody
				private ResponseEntity <programDTO> updateProgramByName(@PathVariable("programName")@NotBlank @NotNull String programName ,@Valid @RequestBody programDTO modifyProgram)// throws ProgramAlreadyExistsException  
				{  
					
					return ResponseEntity.ok(progService.updateProgramByName(programName,modifyProgram));
				} 
			 
			//creating a delete mapping that deletes a specified program  
			@DeleteMapping(path="/deletebyprogid/{programId}",produces = "application/json")  
			@ResponseBody
			private ResponseEntity<?>  deleteByProgramId(@PathVariable("programId")@NotBlank @Positive Integer programId)   
			{  boolean deleted;
			boolean value;
				System.out.println("in delete by programID controller");
				value = progService.deleteByProgramId(programId); 
				deleted = !value;
				if(deleted)
					
					return ResponseEntity.status(HttpStatus.OK).build();
				else
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				//}catch(Exception e) {
					//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
					//System.out.println("Exception : " + e.getMessage());
				//}
				
				
				
			
			}  
			 
			
			
			//creating a delete mapping that deletes a specified program by ProgramName  
				@DeleteMapping(path="/deletebyprogname/{programName}",produces = "application/json")  
				@ResponseBody
				private ResponseEntity<?>  deleteByProgramName(@PathVariable("programName")@NotBlank @NotNull String programName)   
				{  
					System.out.println("in delete by programName controller");
					boolean deleted =progService.deleteByProgramName(programName);
					if(deleted)
						return ResponseEntity.status(HttpStatus.OK).build();
					else
						return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
						
					
				}  
	
}
