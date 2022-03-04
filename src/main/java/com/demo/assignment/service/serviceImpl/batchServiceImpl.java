package com.demo.assignment.service.serviceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.management.ReflectionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.entity.programEntity;
import com.demo.assignment.exception.DuplicateResourceFound;
import com.demo.assignment.exception.ResourceNotFoundException;
import com.demo.assignment.model.batchDTO;
import com.demo.assignment.repo.batchRepository;
import com.demo.assignment.repo.programRepository;
import com.demo.assignment.service.batchService;
import com.demo.assignment.util.batchMapper;




@Service
public class batchServiceImpl implements batchService {
	@Autowired
	   batchRepository batchRepo;
	@Autowired
	programRepository progRepo;
		@Autowired
		batchMapper batchMap;
	
	public List<batchDTO> getAllBatches()  throws ResourceNotFoundException
	{
		List<batchEntity> batchEntityList= batchRepo.findAll();
		if(batchEntityList.size()<=0) {
			throw new ResourceNotFoundException("batch list is not found");
		}
		return (batchMap.toBatchDTOList(batchEntityList));
		
		
	}
	
	public batchDTO getBatchById(Integer batchId) throws ResourceNotFoundException,IllegalArgumentException
	{
		if(batchId!=null)
		{
			if(batchRepo.existsById(batchId)) 
			{
				batchEntity batchEntityById =batchRepo.findById(batchId).get();
				
				return (batchMap.toBatchDTO(batchEntityById));
				
			}else
			{
				throw new ResourceNotFoundException("batch with this: "+batchId +"not found");
			}
		}else
		{
			throw new IllegalArgumentException();
		}
		
		
	}
	
	
	//getall batches by batch_program_id
	public List<batchDTO> getBatchEntitesByProgramId(Integer batch_program_id)throws ResourceNotFoundException
	{
		if(batch_program_id!=null) {
			if(batchRepo.existsById(batch_program_id))
				{
				List<batchEntity> batchEntityList= batchRepo.findAllByBatch_ProgramId( batch_program_id );
				if(batchEntityList.size()<=0)
				{
					System.out.println("no records found with batch_program_id "+batch_program_id);
					throw new ResourceNotFoundException("batch with this: "+batch_program_id +"not found");
				
			}else
			{
				return (batchMap.toBatchDTOList(batchEntityList));
			}
		}
			else
			{
				throw new ResourceNotFoundException("batch with this: "+batch_program_id +"not found");
			}
}else {
	System.out.println("programId is null");
	throw new IllegalArgumentException();
}
	}
	
	
	//creating post  that saves the Batch detail in the database  
			public batchDTO createNewBatch(batchDTO batchDTO)throws  DuplicateResourceFound
			{	
				System.out.println("in start of post method in batchservice Impl");
				batchDTO savedBatchDTO=null;
				batchEntity newCreatedBatch;
				programEntity progEntity; 
				Integer program_batchId= batchDTO.getBatch_program_id();
				
				if(batchDTO != null && program_batchId!=null && batchDTO.getBatch_name()!=null) {
			
				batchEntity newBatchEntity= batchMap.toBatchEntity(batchDTO);
				//progEntity = progRepo.findById(program_batchId).get();
				
				if(progRepo.existsById(program_batchId))
				{
				progEntity = progRepo.findById(program_batchId).get();
					
				List<batchEntity> result = batchRepo.findByBatch_nameAndBatch_program_id(newBatchEntity.getBatch_name(), (newBatchEntity.getProgramEntity_batch()).getProgramId());
				if(result.size()>0) {
					System.out.println("the same combination with batch name and programId exists");
					throw new DuplicateResourceFound("cannot create batch , since already exists with same combination");
				}else {
					
					//save the new batch details in repository since this combination is new
					//set the program entiy details to batch (as one entity has other entity) 
					
					newBatchEntity.setProgramEntity_batch(progEntity);
				
							newCreatedBatch= batchRepo.save(newBatchEntity);
				savedBatchDTO =batchMap.toBatchDTO(newCreatedBatch);
				return savedBatchDTO;
				}
				}else {
					System.out.println("no programid in program table "+program_batchId);
					throw new NoSuchElementException("no programid in program table ");
				  }
				}//end of if
				else {
					System.out.println("check either dto, batchName, batchProgramId is null ");
					throw new NullPointerException();
				}
			}
				
			
			
			//creating put  that updates the program detail by programId
			public batchDTO updateBatchById(Integer batchId,batchDTO modifyDTOBatch) throws ResourceNotFoundException
			{
				System.out.println("in updateBatchId method of batchServiceImpl");
				batchEntity updateLMSBatchEntity;
				batchDTO savedBatchDTO = null;
				batchEntity savedBatchEntity =null;
				if(batchId!=null)
				{
					batchEntity batchEntity = batchMap.toBatchEntity(modifyDTOBatch);
				Boolean isPresentTrue=batchRepo.findById(batchId).isPresent();
				
				if(isPresentTrue)
				{
					updateLMSBatchEntity = batchRepo.getById(batchId);
					updateLMSBatchEntity.setBatch_name(modifyDTOBatch.getBatch_name());
					updateLMSBatchEntity.setBatch_description(modifyDTOBatch.getBatch_description());
					updateLMSBatchEntity.setBatch_num_classes(modifyDTOBatch.getBatch_num_classes());
					updateLMSBatchEntity.setBatch_status(modifyDTOBatch.getBatch_status());
					updateLMSBatchEntity.setCreation_time(modifyDTOBatch.getCreation_time());
					updateLMSBatchEntity.setLast_modified_time(modifyDTOBatch.getLast_modified_time());
					
					
					programEntity updatedProgEntity_Batch = progRepo.getById(modifyDTOBatch.getBatch_program_id());
					
					updateLMSBatchEntity.setProgramEntity_batch(updatedProgEntity_Batch);
					
					savedBatchEntity = batchRepo.save(updateLMSBatchEntity);
					 savedBatchDTO = batchMap.toBatchDTO(savedBatchEntity);
					 
					 return savedBatchDTO; 
				}
				else {
					throw new ResourceNotFoundException("no record found with "+batchId);
				}
				
			}else {
				throw new IllegalArgumentException();
			}
		}
			
			//creating a delete that deletes a specified batch by batchId
			public Boolean deleteByBatchId(Integer batchId) throws ResourceNotFoundException
			{
				System.out.println("in delete by batchId");
				if(batchId!=null) {
					Boolean value= batchRepo.existsById(batchId);
					if(value)
					{
						batchRepo.deleteById(batchId);
						return value;
					}
					else
					{
						System.out.println("record not found with batchId: "+batchId);
						throw new ResourceNotFoundException("record not found with batchid");
					}
					 
				}				
				else
				{
					throw new IllegalArgumentException();
				}
				
			}
			
			//delete by batchName
			public Boolean deleteBybatchName(String batchName) throws ResourceNotFoundException
			{
				Boolean value =false;
				if(!(batchName.isBlank()))
				{
					
				List<batchEntity> deletingEntitesList= batchRepo.findByBatchName(batchName);
				 
				if(deletingEntitesList!=null && deletingEntitesList.size()>0) {
				for(batchEntity rec:deletingEntitesList) {
					batchRepo.delete(rec);
				}
				value= true;
				return value;
				}
				else 
				{
					System.out.println("not found batch with batch name: "+batchName); 
				
					throw new ResourceNotFoundException(("This BatchName is not found in the Database"));
				}
		
			}else
			{
				throw new IllegalArgumentException();
			}
		}			
}
