package com.demo.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.assignment.entity.programEntity;
import com.demo.assignment.exception.DuplicateResourceFound;
import com.demo.assignment.exception.ResourceNotFoundException;
import com.demo.assignment.model.programDTO;


public interface programService {
	    //get all
		public List<programDTO> getAllPrograms() throws ResourceNotFoundException;
		//get by id
		public programDTO getProgramsById(Integer programId)throws ResourceNotFoundException;
		//post
		public programDTO createAndSaveProgram(programDTO program)throws  DuplicateResourceFound;
		//update based on programId
		public programDTO updateProgramById(Integer programId,programDTO programs)throws ResourceNotFoundException;
		//update based on programName
		public programDTO updateProgramByName(String programName,programDTO programs)throws ResourceNotFoundException;
		//delete by programId
		public boolean deleteByProgramId(Integer programId)throws ResourceNotFoundException;
		//delete by programName
		public boolean deleteByProgramName(String programName)throws ResourceNotFoundException;
           
		
		
}
