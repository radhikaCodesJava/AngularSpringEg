package com.demo.assignment.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.entity.programEntity;
import com.demo.assignment.model.programDTO;
import com.demo.assignment.repo.batchRepository;
import com.demo.assignment.repo.programRepository;
import com.demo.assignment.service.programService;
import com.demo.assignment.util.programMapper;
@Service
public class programServiceImpl implements programService{

	@Autowired
	   programRepository progRepo;	
		@Autowired
		programMapper progMap;
	
	public List<programDTO> getAllPrograms()  
	{
		List<programEntity> programEntityList= progRepo.findAll();
	    List<programDTO> programDTOList = progMap.toProgramDTOList(programEntityList);
		return (programDTOList);
		//return programEntityList;
		
	}

	public programDTO getProgramsById(Integer programId) {
		// TODO Auto-generated method stub
		
		programEntity programEntity = progRepo.findById(programId).get();
		
		return (progMap.toProgramDTO(programEntity));
	}
}
