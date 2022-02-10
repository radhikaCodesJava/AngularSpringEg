package com.demo.assignment.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.entity.programEntity;
import com.demo.assignment.repo.batchRepository;
import com.demo.assignment.repo.programRepository;
import com.demo.assignment.service.programService;
@Service
public class programServiceImpl implements programService{

	@Autowired
	   programRepository progRepo;	
		//@Autowired
		 //programMapper progMap;
	
	public List<programEntity> getAllPrograms()  
	{
		List<programEntity> programEntityList= progRepo.findAll();
		//return batchMapper.toBatchDTOList(batchEntityList);
		return programEntityList;
		
	}
}
