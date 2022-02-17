package com.demo.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.assignment.entity.programEntity;
import com.demo.assignment.exception.ResourceNotFoundException;
import com.demo.assignment.model.programDTO;


public interface programService {
	//get 
		public List<programDTO> getAllPrograms()  ;//throws ResourceNotFoundException;
		//public List<programEntity>getAllPrograms();
		//get by id
		public programDTO getProgramsById(Integer programId);// throws ResourceNotFoundException;
		//post
		public programDTO createAndSaveProgram(programDTO program);// throws  ResourceAlreadyExistsExceptions;
		//update based on programId
		public programDTO updateProgramById(Integer programId,programDTO programs);// throws ResourceAlreadyExistsExceptions;
		//update based on programName
		public programDTO updateProgramByName(String programName,programDTO programs);// throws ResourceAlreadyExistsExceptions;
		//delete by programId
		public boolean deleteByProgramId(Integer programId);// throws ResourceNotFoundException;
		//delete by programName
		public boolean deleteByProgramName(String programName);// throws ResourceNotFoundException;

}
