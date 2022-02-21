package com.demo.assignment.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		batchDTO batchDTO= batchService.getBatchById(batchId);
		
		return ResponseEntity.ok(batchDTO);
		
		
		}
	
	//get list of batches given programId
	@GetMapping ( path = "/batchesByProgramId/{batch-programId}", produces = "application/json")
	public ResponseEntity<?> getAllBatchesByBatchProgramId(@PathVariable(value="batch-programId") @Positive Integer batch_program_id)//throws ResourceNotFoundException {
		{
		System.out.println("in getAll batches by Id method");
		//get list of batch entites with given batch-program-Id from req object
		List<batchDTO> batchDTOList= batchService.getBatchEntitesByProgramId(batch_program_id);
		
		
		return ResponseEntity.ok(batchDTOList);
		
		
		}
	
	
	@PostMapping ( path = "/batches", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createBatch(@Valid @RequestBody batchDTO saveBatchDTO )// throws DuplicateBatchException {
	{	
	System.out.println("lmsBatchEntity : " + saveBatchDTO.toString());
	System.out.println("in createBatch method");
		batchDTO savedBatchDTO = batchService.createNewBatch(saveBatchDTO);
				
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBatchDTO);  
	}
	
	
	@PutMapping ( path = "/putbatch/{batchId}", consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> updateBatch(@PathVariable(value="batchId") @Positive Integer batchId, @Valid @RequestBody batchDTO batchDTODetail) throws Exception {
		System.out.println("in update Batch controller");
		return ResponseEntity.ok(batchService.updateBatchById(batchId,batchDTODetail));
		
	}

	
	@DeleteMapping ( path = "/deletebatchbyid/{batchId}", produces = "application/json" )
	public ResponseEntity<?> deleteBatchById(@PathVariable(value="batchId") @Positive Integer batchId) //throws ResourceNotFoundException {
	{
		  Boolean deleted = batchService.deleteByBatchId(batchId); 
		 
		try {
		
		if(deleted)
			
			return ResponseEntity.status(HttpStatus.OK).build();
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		//}catch(Exception e) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			//System.out.println("Exception : " + e.getMessage());
		//}
		finally{
			System.out.println("in finallay of deleteById");
		}
		}
	
	// batchName is not unique in batchTable. there are repeating batchnames.
		/*@DeleteMapping ( path = "/deletebatchbyname/{batchName}", produces = "application/json" )
		public ResponseEntity<?> deleteBatchByName(@PathVariable(value="batchId") @Positive String batchName) //throws ResourceNotFoundException {
		{
			  Boolean deleted = batchService.deleteBybatchName(batchName);;
			try {
			
			if(deleted)
				 
				return ResponseEntity.status(HttpStatus.OK).build();
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}//catch(Exception e) {
				//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				//System.out.println("Exception : " + e.getMessage());
		finally {
			System.out.println("in fianlly of deletebyName");
		}*/
	}


