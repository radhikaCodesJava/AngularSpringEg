package com.demo.assignment.service;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.stereotype.Service;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.model.batchDTO;

@Service
public interface batchService {
	public List<batchDTO> getAllBatches();
	//public List<batchEntity>getAllBatches();

	public batchDTO getBatchesById(@Positive Integer batchId);	
	//public batchEntity getBatchesById(@Positive Integer batchId);
	//public batchDTO createNewBatch(batchDTO batchDTO); //throws DuplicateBatchException;
	
	//public batchDTO updateBatchById(Integer batchId,batchDTO batch);
	//delete by batchId
	//public Boolean deleteByBatchId(Integer batchId);

}
