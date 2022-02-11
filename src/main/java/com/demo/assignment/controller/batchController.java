package com.demo.assignment.controller;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.entity.programEntity;
import com.demo.assignment.model.batchDTO;
import com.demo.assignment.repo.batchRepository;
import com.demo.assignment.service.batchService;


@RestController
@RequestMapping
public class batchController {

	@Autowired 
	batchRepository batchRepo;
	@Autowired
	batchService batchService;
	
	@GetMapping(value="/getBatches",produces="application/json")
	public ResponseEntity<?> getAllBatches()
	{
		System.out.println("in get all batches method");
		//List<batchEntity> listOfBatches = batchRepo.findAll();
		List<batchDTO> listOfBatches = batchService.getAllBatches();
		return ResponseEntity.ok(listOfBatches);
		
	}
	
	@GetMapping ( path = "/batches/{batchId}", produces = "application/json")
	public ResponseEntity<?> getBatchById(@PathVariable(value="batchId") @Positive Integer batchId)//throws ResourceNotFoundException {
		{
		System.out.println("in get batches by Id method");
		//batchEntity batchEntity= batchRepo.getById(batchId);
		batchDTO batchDTO= batchService.getBatchesById(batchId);
		return ResponseEntity.ok(batchDTO);
		
		
		}
}
