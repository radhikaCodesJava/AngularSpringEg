package com.demo.assignment.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;



import com.demo.assignment.entity.programEntity;
import com.demo.assignment.exception.ResourceNotFoundException;
import com.demo.assignment.model.programDTO;
import com.demo.assignment.repo.programRepository;
import com.demo.assignment.service.serviceImpl.programServiceImpl;
import com.demo.assignment.util.programMapper;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ProgramServiceTests {
	@Mock
	private programRepository progRepo= mock(programRepository.class);;
	
	@Mock
	private programMapper progMap =mock(programMapper.INSTANCE.getClass());
	
	@InjectMocks
	programServiceImpl programService;
	
	@BeforeEach
	public void init()
	{
		programService = new programServiceImpl();
	}
	
	@DisplayName("mockito testcase for getProgramsById")
	@Test
	void testGetProgramsById() throws ResourceNotFoundException {
		//Given
		Integer programId = 5;
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		programDTO newProgDTO= new programDTO(5,"DesignPatterns",null,"nonActive",timestamp,timestamp,null);
		//programDTO newProgDTO = new programDTO();
		
		programEntity newProg= new programEntity(5,"DesignPatterns",null, "nonActive",timestamp, timestamp, null);
		//programEntity newProg = new programEntity();
		//programEntity newProg= progMap.toProgramEntity(newProgDTO);
		
		//Integer programId = newProg.getProgramId();
		
		doReturn(Optional.of(newProg)).when(progRepo).findById(programId);
		//Mockito.when(progRepo.findById(programId)).thenReturn(Optional.ofNullable(newProg));
		
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
	void testGetAllPrograms()
	{
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		programDTO newProgDTO= new programDTO(20,"DesignPatterns",null,"nonActive",timestamp,timestamp,null);
		
		programEntity newProg= new programEntity(20,"DesignPatterns",null, "nonActive",timestamp, timestamp, null);
	}

}
