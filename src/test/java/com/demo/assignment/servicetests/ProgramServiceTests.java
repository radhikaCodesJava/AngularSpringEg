package com.demo.assignment.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.assignment.entity.programEntity;
import com.demo.assignment.exception.DuplicateResourceFound;
import com.demo.assignment.exception.ResourceNotFoundException;
import com.demo.assignment.model.programDTO;
import com.demo.assignment.repo.programRepository;
import com.demo.assignment.service.serviceImpl.programServiceImpl;
import com.demo.assignment.util.programMapper;

@ExtendWith(MockitoExtension.class)
public class ProgramServiceTests {
	@Mock
	private programRepository progRepo; //= mock(programRepository.class);
	
	@Mock
	private programMapper progMap; //=mock(programMapper.INSTANCE.getClass());
	
	@InjectMocks
	
	programServiceImpl programService;
	
	/*@BeforeEach
	public void init()
	{
		//programService = new programServiceImpl();
	}*/
	
	@DisplayName("mockito testcase for getProgramsById")
	@Test
	void testGetProgramById() throws ResourceNotFoundException {
		//Given
		Integer programId = 5;
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		programDTO newProgDTO= new programDTO(5,"DesignPatterns",null,"nonActive",timestamp,timestamp,null);
		//programDTO newProgDTO = new programDTO();
		
		programEntity newProg= new programEntity(5,"DesignPatterns",null, "nonActive",timestamp, timestamp, null);
		//programEntity newProg = new programEntity();
				
		//doReturn(Optional.of(newProg)).when(progRepo).findById(programId);
		Mockito.when(progRepo.existsById(programId)).thenReturn(true);
		
		Mockito.when(progRepo.findById(programId)).thenReturn(Optional.of(newProg));
		
		Mockito.when(progMap.toProgramDTO(newProg)).thenReturn(newProgDTO);
		//doReturn(Optional.of(newProgDTO)).when(progMap).toProgramDTO(newProg);
		
		//when or Action
		programDTO resultFromService = programService.getProgramsById(programId);
		
		
		//then or Assert 
		assertThat(resultFromService).isNotNull();
		assertThat(resultFromService.getProgram_name()).isEqualTo(newProgDTO.getProgram_name());
		assertThat(resultFromService.getProgram_description()).isEqualTo(newProgDTO.getProgram_description());
		assertThat(resultFromService).isSameAs(newProgDTO);
		
		verify(progRepo).findById(programId);
		verify(progMap, times(1)).toProgramDTO(newProg);
		
			
	}
	
	@Test
	void testGetAllPrograms() throws ResourceNotFoundException
	{
		//Given
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		programEntity newProg= new programEntity(20,"DesignPatterns",null, "nonActive",timestamp, timestamp, null);
		
		List<programEntity> ListofEntites =new ArrayList<>();
		
		ListofEntites.add(newProg);
		
		
		programDTO newProgDTO= new programDTO(20,"DesignPatterns",null,"nonActive",timestamp,timestamp,null);
		
		List<programDTO> ListofDTOs = new ArrayList<>();
		
		ListofDTOs.add(newProgDTO);
		
		
		Mockito.when(progRepo.findAll()).thenReturn(ListofEntites);
		
		Mockito.when(progMap.toProgramDTOList(ListofEntites)).thenReturn(ListofDTOs);
		
		
		//when or Action
		List<programDTO> fetchedFromService = programService.getAllPrograms();
		
		//then or Assert
		assertThat(fetchedFromService).isNotNull();
		assertThat(fetchedFromService.size()).isEqualTo(ListofDTOs.size());
	    assertThat(fetchedFromService).isSameAs(ListofDTOs);
	
	    verify(progRepo).findAll();
	    verify(progMap).toProgramDTOList(ListofEntites);
	}
	
	//createAndSaveProgram 
	@Test
	void testcreateAndSaveProgram() throws ResourceNotFoundException, DuplicateResourceFound
	{
		//Given
		Integer programId =50;
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		programDTO reqDTO = new programDTO(50,"SpringTesting",null,"nonActive",timestamp,timestamp,null);
		
		programEntity newProg= new programEntity(50,"SpringTesting",null, "nonActive",timestamp, timestamp, null);
		
		List<programEntity> ListofEntites =new ArrayList<>();
		
		//ListofEntites.add(newProg);
		//not adding because ,  there should not be existing records with new program name,
		//so the result of query to db should return list with zero records.
		
		programEntity savedEntity = new programEntity(50,"SpringTesting",null, "nonActive",timestamp, timestamp, null);
		
		programDTO newProgDTO= new programDTO(50,"SpringTesting",null,"nonActive",timestamp,timestamp,null);
		
		
		
		Mockito.when(progMap.toProgramEntity(reqDTO)).thenReturn(newProg);
		
		Mockito.when(progRepo.findByProgramName(reqDTO.getProgram_name())).thenReturn(ListofEntites);
		
		Mockito.when(progRepo.save(newProg)).thenReturn(savedEntity);
		
		//Mockito.when(progMap.toProgramDTO(savedEntity)).thenReturn(newProgDTO);
		
		
		//when or Action
		programDTO fetchedFromService = programService.createAndSaveProgram(reqDTO);
		
		//then or Assert
		assertThat(fetchedFromService).isNotNull();
		assertThat(fetchedFromService.getProgram_description()).isEqualTo(newProgDTO.getProgram_description());
		assertThat(fetchedFromService.getProgram_name()).isEqualTo(reqDTO.getProgram_name());
	    //assertThat(fetchedFromService).isSameAs(newProgDTO);
		//assertThat(reqDTO).isSameAs(fetchedFromService);
		assertThat(newProgDTO).isNotNull();
		
		verify(progMap).toProgramEntity(reqDTO);
		verify(progRepo).findByProgramName(reqDTO.getProgram_name());
		verify(progRepo).save(newProg);
		//verify(progMap).toProgramDTO(savedEntity);
	
	}
	
	//updateProgramById
	@Test
	void testupdateProgramById() throws ResourceNotFoundException, DuplicateResourceFound
	{
		//Given
		Integer programId =50;
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		programDTO reqDTO = new programDTO(50,"SpringTesting",null,"nonActive",timestamp,timestamp,null);
		
		programEntity toBeUpdatedEntity= new programEntity(50,"DesignPatterns","new Course", "Active",timestamp, timestamp, null);
		
		programEntity savedEntity = new programEntity(50,"SpringTesting",null, "nonActive",timestamp, timestamp, null);
		
		programDTO newProgDTO= new programDTO(50,"SpringTesting",null,"nonActive",timestamp,timestamp,null);
		
		
		Mockito.when(progRepo.existsById(programId)).thenReturn(true);
		
		Mockito.when(progRepo.findById(programId)).thenReturn(Optional.of(toBeUpdatedEntity));
		
		Mockito.when(progRepo.save(toBeUpdatedEntity)).thenReturn(savedEntity);
		
		//Mockito.when(progMap.toProgramDTO(savedEntity)).thenReturn(newProgDTO);
		
		
		//when or Action
		programDTO fetchedFromService = programService.updateProgramById(programId,reqDTO);
		
		//then or Assert
		assertThat(fetchedFromService).isNotNull();
		assertThat(fetchedFromService.getProgram_description()).isEqualTo(reqDTO.getProgram_description());
		assertThat(fetchedFromService.getProgram_name()).isEqualTo(reqDTO.getProgram_name());
	    //assertThat(fetchedFromService).isSameAs(newProgDTO);
		//assertThat(reqDTO).isSameAs(fetchedFromService);
		assertThat(newProgDTO).isNotNull();
		
		verify(progRepo).existsById(programId);
		verify(progRepo).findById(programId);
		verify(progRepo).save(toBeUpdatedEntity);
		
		//verify(progMap).toProgramDTO(savedEntity);
		
	
	}
	
	//updateProgramByName
	@Test
	void testUpdateProgramByName() throws ResourceNotFoundException, DuplicateResourceFound
	{
		//Given
		String programName ="DesignPatterns";
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		programDTO reqDTO = new programDTO(50,"SpringTesting",null,"nonActive",timestamp,timestamp,null);
		
		programEntity toBeUpdatedEntity= new programEntity(50,"DesignPatterns","new Course", "Active",timestamp, timestamp, null);
		
		List<programEntity> ListOfEntitesWithProgName = new ArrayList<>();
		ListOfEntitesWithProgName.add(toBeUpdatedEntity);
		
		programEntity savedEntity = new programEntity(50,"SpringTesting",null, "nonActive",timestamp, timestamp, null);
		
		programDTO newProgDTO= new programDTO(50,"SpringTesting",null,"nonActive",timestamp,timestamp,null);
		
		
		
		Mockito.when(progRepo.findByProgramName(programName)).thenReturn(ListOfEntitesWithProgName);
		
		Mockito.when(progRepo.save(toBeUpdatedEntity)).thenReturn(savedEntity);
		
		//Mockito.when(progMap.toProgramDTO(savedEntity)).thenReturn(newProgDTO);
		
		
		//when or Action
		programDTO fetchedFromService = programService.updateProgramByName(programName,reqDTO);
		
		//then or Assert
		assertThat(fetchedFromService).isNotNull();
		assertThat(fetchedFromService.getProgram_description()).isEqualTo(reqDTO.getProgram_description());
		assertThat(fetchedFromService.getProgram_name()).isEqualTo(reqDTO.getProgram_name());
	    //assertThat(fetchedFromService).isSameAs(newProgDTO);
		//assertThat(reqDTO).isSameAs(fetchedFromService);
		assertThat(newProgDTO).isNotNull();
		
		verify(progRepo).findByProgramName(programName);
		verify(progRepo).save(toBeUpdatedEntity);
		//verify(progMap).toProgramDTO(savedEntity);
	
	}
	//deleteByProgramId
	@Test
	void testDeleteByProgramId() throws ResourceNotFoundException, DuplicateResourceFound
	{
		//Given
		Integer programId =5;
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		programEntity deletingEntity = new programEntity(50,"SpringTesting",null, "nonActive",timestamp, timestamp, null);
		
		//programDTO DeletedDTO= new programDTO(50,"SpringTesting",null,"nonActive",timestamp,timestamp,null);
		
		
		Mockito.when(progRepo.existsById(programId)).thenReturn(true);
		
		Mockito.when(progRepo.findById(programId)).thenReturn(Optional.of(deletingEntity));
		
		//Mockito.when(progRepo.delete(deletingEntity)).then();
		
		//when or Action
		boolean value =programService.deleteByProgramId(programId);
		
		//then or Assert
		assertThat(value).isEqualTo(true);
		verify(progRepo, times(1)).existsById(programId);
		verify(progRepo,times(1)).findById(programId);
		verify(progRepo).delete(deletingEntity);
		//verify(progRepo, times(1)).deleteById(programId);
	
	}
	
	//deleteByProgramName
	@Test
	void testDeleteByProgramName() throws ResourceNotFoundException, DuplicateResourceFound
	{
		//Given
		String programName ="Spring MVC";
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		programEntity toBeDeletedEntity= new programEntity(50,"Spring MVC","new Course", "Active",timestamp, timestamp, null);
		
		List<programEntity> ListOfEntitesWithProgName = new ArrayList<>();
		ListOfEntitesWithProgName.add(toBeDeletedEntity);
		
		Mockito.when(progRepo.findByProgramName(programName)).thenReturn(ListOfEntitesWithProgName);
		
		//Mockito.when(progRepo.delete(deletingEntity)).then();
		
		//when or Action
		boolean value =programService.deleteByProgramName(programName);
		
		//then or Assert
		assertThat(value).isEqualTo(true);
		verify(progRepo,times(1)).findByProgramName(programName);
		verify(progRepo).delete(toBeDeletedEntity);
		
	}
}
