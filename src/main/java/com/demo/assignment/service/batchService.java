package com.demo.assignment.service;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.stereotype.Service;

import com.demo.assignment.model.batchDTO;

@Service
public interface batchService {
	public List<batchDTO> getAllBatches();
	
	public batchDTO getBatchById(@Positive Integer batchId);
	
	//public List<batchDTO> getBatchAllBatchesById(Integer batchId);
	
	public batchDTO createNewBatch(batchDTO batchDTO); //throws DuplicateBatchException;
	
	public batchDTO updateBatchById(Integer batchId,batchDTO batch);
	
	public Boolean deleteByBatchId(Integer batchId);
	
	public Boolean deleteBybatchName(String batchName);

}
