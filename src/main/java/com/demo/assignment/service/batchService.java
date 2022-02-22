package com.demo.assignment.service;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.stereotype.Service;

import com.demo.assignment.exception.DuplicateResourceFound;
import com.demo.assignment.exception.ResourceNotFoundException;
import com.demo.assignment.model.batchDTO;

@Service
public interface batchService {
	public List<batchDTO> getAllBatches()throws ResourceNotFoundException;
	
	public batchDTO getBatchById(@Positive Integer batchId)throws ResourceNotFoundException;
	
	public List<batchDTO> getBatchEntitesByProgramId(Integer batch_program_id)throws ResourceNotFoundException;
	
	public batchDTO createNewBatch(batchDTO batchDTO) throws  DuplicateResourceFound;
	
	public batchDTO updateBatchById(Integer batchId,batchDTO batch)throws ResourceNotFoundException;
	
	public Boolean deleteByBatchId(Integer batchId)throws ResourceNotFoundException;
	
	public Boolean deleteBybatchName(String batchName)throws ResourceNotFoundException;

}
