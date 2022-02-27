package com.demo.assignment.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.demo.assignment.entity.programEntity;
import com.demo.assignment.exception.DuplicateResourceFound;
import com.demo.assignment.exception.ResourceNotFoundException;
import com.demo.assignment.model.programDTO;

import com.demo.assignment.repo.programRepository;
import com.demo.assignment.service.programService;
import com.demo.assignment.util.programMapper;

@Service
public class programServiceImpl implements programService{

	@Autowired
	   programRepository progRepo;	
	@Autowired
		programMapper progMap;
	
	
	public List<programDTO> getAllPrograms()  throws ResourceNotFoundException
	{ 
		List<programEntity> programEntityList= progRepo.findAll();
		if(programEntityList.size()<=0) {
			throw new ResourceNotFoundException("programs list is not found");
		}
		else {
	    
	    return (progMap.toProgramDTOList(programEntityList));
	  }
	}

	public programDTO getProgramsById(Integer programId) throws ResourceNotFoundException
	
	{
		if(programId!= null) {
			
		
		if(progRepo.existsById(programId))
		{
			programEntity programEntity = progRepo.findById(programId).get();
			return (progMap.toProgramDTO(programEntity));
		
		}
		else {
			throw new ResourceNotFoundException("program with this: "+programId +"not found");
		 }
		}else
		{
			throw new IllegalArgumentException();
		}
		//programEntity programEntity  = progRepo.findById(programId)
	    //        .orElseThrow(() -> new ResourceNotFoundException("programId not found for this id :: " + programId));
		
	
	}
	
	
	//post
			public programDTO createAndSaveProgram(programDTO program)throws  DuplicateResourceFound
			{
				programEntity newProgramEntity = progMap.toProgramEntity(program);
				 programDTO savedProgramDTO =null;
				 
				 List<programEntity>result= progRepo.findByProgramName(newProgramEntity.getProgram_name());
				 if(result.size()>0) {
					 throw new DuplicateResourceFound("cannot create program , since already exists");
				 }else {
					 savedProgramDTO= progMap.INSTANCE.toProgramDTO(progRepo.save(newProgramEntity));
				 		return (savedProgramDTO);
				 }
					  
			}
	      
			//update based on programId
			public programDTO updateProgramById(Integer programId,programDTO program)throws ResourceNotFoundException
			{
				programEntity updateLMSProgramEntity =null;
				
				programDTO savedProgramDTO =null;
				
				if(programId!= null) {
				Boolean isTrue=progRepo.findById(programId).isEmpty();
				 if(isTrue) {
					 System.out.println("program with "+ programId+"not found");
				 throw new ResourceNotFoundException("program with id"+programId +"not found");
					}
				else {
			    
					updateLMSProgramEntity= progRepo.findById(programId).get();
					updateLMSProgramEntity.setProgram_name(program.getProgram_name());
					updateLMSProgramEntity.setProgram_description(program.getProgram_description());
					updateLMSProgramEntity.setProgram_status(program.getProgram_status());
					updateLMSProgramEntity.setCreation_time(program.getCreation_time());
					updateLMSProgramEntity.setLast_mod_time(program.getLast_mod_time());
					
					
					 savedProgramDTO =progMap.INSTANCE.toProgramDTO(progRepo.save(updateLMSProgramEntity));
					 return savedProgramDTO;
				}
			}else
			{
				throw new IllegalArgumentException();
			}
		}
			
			//update based on programName
			public programDTO updateProgramByName(String programName,programDTO program)throws ResourceNotFoundException
			{
				programEntity updateProgramEntity =null;
				if(!(programName.isEmpty())) {
					
				
				List<programEntity>result= progRepo.findByProgramName(programName);
				if(result.size()<=0) {
					System.out.println("in update program, no program name list is found");
					throw new ResourceNotFoundException("no list with such program name"+programName);
				}else {
				 for(programEntity rec:result) {
					 
					if( rec.getProgram_name().equalsIgnoreCase(programName)) {
					
						updateProgramEntity =rec;
					}
				 
				 }
				 //updateProgramEntity.setProgram_id(program.getProgram_id());
				 updateProgramEntity.setProgram_name(program.getProgram_name());
				 updateProgramEntity.setProgram_description(program.getProgram_description());
				 updateProgramEntity.setProgram_status(program.getProgram_status());
				 updateProgramEntity.setCreation_time(program.getCreation_time());
				 updateProgramEntity.setLast_mod_time(program.getLast_mod_time());
						//updateProgramEntity= progMap.INSTANCE.toProgramEntity(program);
						progRepo.save(updateProgramEntity);
					
			 		return progMap.INSTANCE.toProgramDTO(updateProgramEntity);
				}
				
			 }//end of else
				else
				{
					throw new IllegalArgumentException();
				}
			}//end of method
			
			
			//delete by programId
			public boolean deleteByProgramId(Integer programId)throws ResourceNotFoundException
			{
				 System.out.println("in delete program by id method");
				 if(programId!=null)
				 {
					 Boolean value= progRepo.existsById(programId);
					 if(value) {
						 programEntity progEntity= progRepo.findById(programId).get();
						 progRepo.delete(progEntity);
						 return value;
					 }else {
						 System.out.println("no record found with programId"+programId);
						 throw new ResourceNotFoundException("no record found with programId");
					 }
					 
				 }
				 else
					 {throw new IllegalArgumentException();}
				}	
			
			
			//delete by programName
			public boolean deleteByProgramName(String programName)throws ResourceNotFoundException
			{ 
				boolean deleted = false;
				programEntity deletedLMSProgramEntity =null;
				System.out.println("in deleteByprogramName impl");
				if(!(programName.isBlank())) {
					
				List<programEntity> result= progRepo.findByProgramName(programName);
				
					if(result.size()<=0) {
					System.out.println("no record found with progarmName:"+programName);
					throw new ResourceNotFoundException("no record found with programName");
				}else {
				 for(programEntity eachRec:result) {
					 
					if( eachRec.getProgram_name().equalsIgnoreCase(programName)) {
					
						deletedLMSProgramEntity =eachRec;
					}
			     }
				progRepo.delete(deletedLMSProgramEntity);
				deleted =true;
				return deleted;
				}
					
			}	else
			{
				throw new IllegalArgumentException();
			}
				
			}
}

