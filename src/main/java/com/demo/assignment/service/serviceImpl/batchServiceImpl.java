package com.demo.assignment.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.entity.programEntity;
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
	
	public List<batchDTO> getAllBatches()  
	{
		List<batchEntity> batchEntityList= batchRepo.findAll();
		
		return (batchMap.toBatchDTOList(batchEntityList));
		
		
	}
	
	public batchDTO getBatchById(Integer batchId) {
		
		batchEntity batchEntityById =batchRepo.findById(batchId).get();
		//return batchEntityById;
		return (batchMap.toBatchDTO(batchEntityById));
	}
	
	
	//getall batches by batch_program_id
	public List<batchDTO> getBatchEntitesByProgramId(Integer batch_program_id)
		
	{
		List<batchEntity> batchEntityList= batchRepo.findAllByBatch_ProgramId( batch_program_id );
		
		return (batchMap.toBatchDTOList(batchEntityList));
		
		
	}
	
	
	//creating post  that saves the Batch detail in the database  
			public batchDTO createNewBatch(batchDTO batchDTO)// throws DuplicateBatchException { 			
			{	
				System.out.println("in start of post method in batchservice Impl");
			batchDTO savedBatchDTO=null;
			batchEntity newCreatedBatch;
			programEntity progEntity; 
			Integer program_batchId= batchDTO.getBatch_program_id();
				batchEntity newBatchEntity= batchMap.toBatchEntity(batchDTO);
				try {
									
				List<batchEntity> result = batchRepo.findByBatch_nameAndBatch_program_id(newBatchEntity.getBatch_name(), (newBatchEntity.getProgramEntity_batch()).getProgramId());
				if(!(result.size()>0)) {
					//save the new batch details in repository since this combination is new
					//set the program entiy details to batch (as one entity has other entity) 
					progEntity = progRepo.findById(program_batchId).get();
				
					newBatchEntity.setProgramEntity_batch(progEntity);
				System.out.println("newBatchEntity : " + newBatchEntity.toString());
							newCreatedBatch= batchRepo.save(newBatchEntity);
				savedBatchDTO =batchMap.toBatchDTO(newCreatedBatch);
				}
				else {
					System.out.println("the same combination with batch name and programId exists");
				}
				
				return savedBatchDTO;
				}
				/*}catch(Exception e)
				{
					throw new DuplicateBatchException ( " Batch Name:" + newBatchEntity.getBatch_name() + "-ProgramId:" + (newBatchEntity.getLmsprogram()).getProgram_id() 
			                + " combination already exists; Please give a different combination"); 
				}*/
				finally{
					System.out.println("in post method of batch serviceImpl");
				}
			}
			
			//creating put  that updates the program detail by programId
			public batchDTO updateBatchById(Integer batchId,batchDTO modifyBatch) //throws DuplicateBatchException{
			{
				System.out.println("in updateBatchId method of batchServiceImpl");
				batchEntity updateLMSBatchEntity;
				batchDTO savedBatchDTO = null;
				try {
					batchEntity batchEntity = batchMap.toBatchEntity(modifyBatch);
				Boolean isPresentTrue=batchRepo.findById(batchId).isPresent();
				
				if(isPresentTrue)
				{
					updateLMSBatchEntity = batchRepo.getById(batchId);
					updateLMSBatchEntity.setBatch_name(modifyBatch.getBatch_name());
					updateLMSBatchEntity.setBatch_description(modifyBatch.getBatch_description());
					updateLMSBatchEntity.setBatch_num_classes(modifyBatch.getBatch_num_classes());
					updateLMSBatchEntity.setBatch_status(modifyBatch.getBatch_status());
					updateLMSBatchEntity.setCreation_time(modifyBatch.getCreation_time());
					updateLMSBatchEntity.setLast_modified_time(modifyBatch.getLast_modified_time());
					
					
					programEntity updatedProgEntity_Batch = progRepo.getById(modifyBatch.getBatch_program_id());
					
					updateLMSBatchEntity.setProgramEntity_batch(updatedProgEntity_Batch);
					
					
					 savedBatchDTO = batchMap.toBatchDTO(batchRepo.save(updateLMSBatchEntity));
					 
					 
				}
				}catch(Exception e){
					System.out.println("Exception : " + e.getMessage());
				}
				return savedBatchDTO; 
			}
			
			//creating a delete that deletes a specified batch by batchId
			public Boolean deleteByBatchId(Integer batchId)
			{
				System.out.println("in delete by batchId");
				//Boolean value=batchRepo.findById(batchId).isEmpty();
				Boolean value= batchRepo.existsById(batchId);
				if(value)
					
				 batchRepo.deleteById(batchId);
				else
					System.out.println("batch id"+ batchId+"not found");
				
				return value;
		
			}
			
			public Boolean deleteBybatchName(String batchName) {
				
				batchEntity deletingEntity= batchRepo.findByBatchName(batchName);
				Boolean value =false; 
				if(deletingEntity!=null) {
				batchRepo.delete(deletingEntity);
				value= true;
				}
				//new ResourceNotFoundException(("This BatchId-" + batchId + " is not found in the Database"));
				else 
				System.out.println("not found batch with batch name: "+batchName); 
				
				
				return value;
			}
					
}
