package com.demo.assignment.service.serviceImpl;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.model.batchDTO;
import com.demo.assignment.repo.batchRepository;
import com.demo.assignment.service.batchService;
import com.demo.assignment.util.batchMapper;


@Service
public class batchServiceImpl implements batchService {
	@Autowired
	   batchRepository batchRepo;	
		@Autowired
		batchMapper batchMap;
	
	public List<batchDTO> getAllBatches()  
	{
		List<batchEntity> batchEntityList= batchRepo.findAll();
		return (batchMap.toBatchDTOList(batchEntityList));
		//return batchEntityList;
		
	}
	
	public batchDTO getBatchesById(Integer batchId) {
		
		batchEntity batchEntityById =batchRepo.findById(batchId).get();
		//return batchEntityById;
		return (batchMap.toBatchDTO(batchEntityById));
	}
}
