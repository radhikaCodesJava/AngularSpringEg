package com.demo.assignment.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.demo.assignment.entity.programEntity;
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
	
	public List<programDTO> getAllPrograms()  
	{
		List<programEntity> programEntityList= progRepo.findAll();
	    List<programDTO> programDTOList = progMap.toProgramDTOList(programEntityList);
		return (programDTOList);
		//return programEntityList;
		
	}

	public programDTO getProgramsById(Integer programId) {
		// TODO Auto-generated method stub
		
		programEntity programEntity = progRepo.findById(programId).get();
		
		return (progMap.toProgramDTO(programEntity));
	}
	
	
	//post
			public programDTO createAndSaveProgram(programDTO program)// throws  ResourceAlreadyExistsExceptions;
			{
				programEntity newProgramEntity = progMap.toProgramEntity(program);
				 programDTO savedProgramDTO =null;
				 //try {
				 List<programEntity>result= progRepo.findByProgramName(newProgramEntity.getProgram_name());
				 if(!(result.size()>0))
					 savedProgramDTO= progMap.INSTANCE.toProgramDTO(progRepo.save(newProgramEntity));
				 
				 
					return (savedProgramDTO); 
				//}catch(Exception e) {
					//throw new ProgramAlreadyExistsException ( " Program Name:" + newProgramEntity.getProgram_name() + " program already exists; Please give a different new program"); 
				//}
					
			}
	      
			//update based on programId
			public programDTO updateProgramById(Integer programId,programDTO program)// throws ResourceAlreadyExistsExceptions;
			{
				programEntity updateLMSProgramEntity =null;
				
				programDTO savedProgramDTO =null;
				
				//try {
				Boolean isTrue=progRepo.findById(programId).isEmpty();
				 if(isTrue)
				 //throw new ProgramNotFoundException("program with id"+programId +"not found");
					 System.out.println("program with "+ programId+"not found");
				else {
			    /*updateLMSProgram= programRepository.findById(programId).get();
				updateLMSProgram.setProgram_name(programs.getProgram_name());
				updateLMSProgram.setProgram_description(programs.getProgram_description());
				updateLMSProgram.setProgram_status(programs.getProgram_status());
				updateLMSProgram.setCreation_time(programs.getCreation_time());
				updateLMSProgram.setLast_mod_time(programs.getLast_mod_time());*/
			    
					 //updateLMSProgramEntity= progMap.toProgramEntity(program);
					updateLMSProgramEntity= progRepo.findById(programId).get();
					updateLMSProgramEntity.setProgram_name(program.getProgram_name());
					updateLMSProgramEntity.setProgram_description(program.getProgram_description());
					updateLMSProgramEntity.setProgram_status(program.getProgram_status());
					updateLMSProgramEntity.setCreation_time(program.getCreation_time());
					updateLMSProgramEntity.setLast_mod_time(program.getLast_mod_time());
					
					
					 savedProgramDTO =progMap.INSTANCE.toProgramDTO(progRepo.save(updateLMSProgramEntity));
				}
			return savedProgramDTO;
			}
			
			//update based on programName
			public programDTO updateProgramByName(String programName,programDTO program)// throws ResourceAlreadyExistsExceptions;
			{
				programEntity updateProgramEntity =null;
				
				List<programEntity>result= progRepo.findByProgramName(programName);
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
			
			
			//delete by programId
			public boolean deleteByProgramId(Integer programId)// throws ResourceNotFoundException;
			{
				 System.out.println("in delete program by id method");
				 programEntity progEntity= progRepo.findById(programId).get();
				 boolean value=progRepo.findById(programId).isEmpty();
				 /* value is false, means rec exists with programId
				  * value is true, means rec does not exists with programId */
				 
				 if(!(value)) //isempty() returns false if value is present.
					 progRepo.delete(progEntity);
				else 
				 System.out.println("program with this id"+programId+ "is not found");
					//throw new ProgramNotFoundException();
				
				 return value;
			}
			
			//delete by programName
			public boolean deleteByProgramName(String programName)// throws ResourceNotFoundException;
			{
				boolean deleted = false;
				System.out.println("in deleteByprogramName impl");
				List<programEntity> result= progRepo.findByProgramName(programName);
				programEntity deletedLMSProgramEntity =null;
				
				 for(programEntity eachRec:result) {
					 
					if( eachRec.getProgram_name().equalsIgnoreCase(programName)) {
					
						deletedLMSProgramEntity =eachRec;
					}
					 
				 }
				
				
				if(result.size()>0)	{
					progRepo.delete(deletedLMSProgramEntity);
				deleted =true;
				}
				else 	
				//throw new ProgramNotFoundException();
					System.out.println("program with this id"+programName+ "is not found");
				
				return deleted;
			
}
}

