package com.demo.assignment.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.entity.programEntity;
import com.demo.assignment.exception.DuplicateResourceFound;
import com.demo.assignment.exception.ResourceNotFoundException;
import com.demo.assignment.model.batchDTO;
import com.demo.assignment.repo.batchRepository;
import com.demo.assignment.repo.programRepository;
import com.demo.assignment.service.serviceImpl.batchServiceImpl;

import com.demo.assignment.util.batchMapper;


@ExtendWith(MockitoExtension.class)
public class BatchServiceUnitTests {
	@Mock
	private batchRepository batchRepo; //= mock(batchRepository.class);
	
	@Mock
	private programRepository progRepo;
	
	@Mock
	private batchMapper batchMap; //=mock(batchMapper.INSTANCE.getClass());
	
	@InjectMocks
	
	batchServiceImpl batchService;
	
	/*@BeforeEach
	public void init()
	{
		//batchService = new batchServiceImpl();
	}*/
	
	@DisplayName("mockito testcase for getBatchesById")
	@Test
	void testGetBatchById() throws ResourceNotFoundException {
		//Given
		Integer batchId = 5;
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		batchDTO  BatchDTO= new batchDTO(5,"DesignPatterns",null,"nonActive",6,timestamp,timestamp,null);
		//batchDTO new BatchDTO = new batchDTO();
		
		batchEntity newBatch= new batchEntity(5,"DesignPatterns",null, "nonActive",6, timestamp, timestamp, null);
		//batchEntity newBatch = new batchEntity();
				
		//doReturn(Optional.of(newBatch)).when(batchRepo).findById(batchId);
		Mockito.when(batchRepo.existsById(batchId)).thenReturn(true);
		
		Mockito.when(batchRepo.findById(batchId)).thenReturn(Optional.of(newBatch));
		
		Mockito.when(batchMap.toBatchDTO(newBatch)).thenReturn(BatchDTO);
		//doReturn(Optional.of(new BatchDTO)).when(batchMap).toBatchDTO(newBatch);
		
		//when or Action
		batchDTO resultFromService = batchService.getBatchById(batchId);
		
		
		//then or Assert 
		assertThat(resultFromService).isNotNull();
		assertThat(resultFromService.getBatch_name()).isEqualTo(BatchDTO.getBatch_name());
		assertThat(resultFromService.getBatch_description()).isEqualTo(BatchDTO.getBatch_description());
		assertThat(resultFromService).isSameAs(BatchDTO);
		
		verify(batchRepo).findById(batchId);
		verify(batchMap, times(1)).toBatchDTO(newBatch);
		
			
	}
	
	@Test
	void testGetAllBatches() throws ResourceNotFoundException
	{
		//Given
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		batchEntity newBatch1= new batchEntity(20,"DesignPatterns",null, "nonActive",6,timestamp, timestamp, null);
		batchEntity newBatch2= new batchEntity(21,"springTxnBasics",null, "nonActive",6,timestamp, timestamp, null);
		batchEntity newBatch3= new batchEntity(22,"Lombok",null, "nonActive",6,timestamp, timestamp, null);
		
		List<batchEntity> ListofEntites =new ArrayList<>();
		
		ListofEntites.add(newBatch1);
		ListofEntites.add(newBatch2);
		ListofEntites.add(newBatch3);
		
		
		batchDTO BatchDTO1= new batchDTO(20,"DesignPatterns",null,"nonActive",6,timestamp,timestamp,null);
		batchDTO BatchDTO2= new batchDTO(21,"springTxnBasics",null,"nonActive",6,timestamp,timestamp,null);
		batchDTO BatchDTO3= new batchDTO(22,"Lombok",null,"nonActive",6,timestamp,timestamp,null);
		
		List<batchDTO> ListofDTOs = new ArrayList<>();
		
		ListofDTOs.add(BatchDTO1);
		ListofDTOs.add(BatchDTO2);
		ListofDTOs.add(BatchDTO3);
		
		
		Mockito.when(batchRepo.findAll()).thenReturn(ListofEntites);
		
		Mockito.when(batchMap.toBatchDTOList(ListofEntites)).thenReturn(ListofDTOs);
		
		
		//when or Action
		List<batchDTO> fetchedFromService = batchService.getAllBatches();
		
		//then or Assert
		assertThat(fetchedFromService).isNotNull();
		assertThat(fetchedFromService.size()).isEqualTo(ListofDTOs.size());
	    assertThat(fetchedFromService).isSameAs(ListofDTOs);
	
	    verify(batchRepo).findAll();
	    verify(batchMap).toBatchDTOList(ListofEntites);
	}
	
	//createAndSaveBatch 
	@Test
	void testcreateAndSaveBatch() throws ResourceNotFoundException, DuplicateResourceFound
	{
		//Given
		Integer batchId =50;
		Integer program_batchId =15;
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		batchDTO reqDTO = new batchDTO(50,"SpringTesting",null,"nonActive",6,timestamp,timestamp,15);
		
		programEntity progEntity = new programEntity(15,"SpringTesting",null, "active",timestamp,timestamp,null);
		
		batchEntity newBatchEntity= new batchEntity(50,"SpringTesting",null, "nonActive",6,timestamp, timestamp, progEntity);
		
		List<batchEntity> ComboListOfProgIdBatchName =new ArrayList<>();
		
		//ComboListOfProgIdBatchName.add(exisitngEntityInRepo);
			
		batchEntity createdBatchEntity = new batchEntity(50,"SpringTesting",null, "nonActive",6,timestamp, timestamp, progEntity);
		
		batchDTO BatchDTO= new batchDTO(50,"SpringTesting",null,"nonActive",6,timestamp,timestamp,15);
		
		
		Mockito.when(batchMap.toBatchEntity(BatchDTO)).thenReturn(newBatchEntity);
		
		Mockito.when(progRepo.existsById(program_batchId)).thenReturn(true);
		
		Mockito.when(progRepo.findById(program_batchId)).thenReturn(Optional.of(progEntity));
				
		Mockito.when(batchRepo.findByBatch_nameAndBatch_program_id(reqDTO.getBatch_name(), program_batchId)).thenReturn(ComboListOfProgIdBatchName);
		
		Mockito.when(batchRepo.save(newBatchEntity)).thenReturn(createdBatchEntity);
		
		Mockito.when(batchMap.toBatchDTO(createdBatchEntity)).thenReturn(BatchDTO);
						
		//when or Action
		batchDTO fetchedFromService = batchService.createNewBatch(reqDTO);
		
		//then or Assert
		assertThat(fetchedFromService).isNotNull();
		assertThat(fetchedFromService.getBatch_description()).isEqualTo(BatchDTO.getBatch_description());
		assertThat(fetchedFromService.getBatch_name()).isEqualTo(reqDTO.getBatch_name());
	    //assertThat(fetchedFromService).isSameAs(new BatchDTO);
		//assertThat(reqDTO).isSameAs(fetchedFromService);
		assertThat(BatchDTO).isNotNull();
		
		verify(progRepo).findById(program_batchId);
		verify(batchRepo).findByBatch_nameAndBatch_program_id(reqDTO.getBatch_name(), program_batchId);
		verify(batchRepo).save(newBatchEntity);
		//verify(batchMap).toBatchDTO(createdBatchEntity);
	
	}
	
	//updateBatchById
	@Test
	void testupdateBatchById() throws ResourceNotFoundException, DuplicateResourceFound
	{
		//Given
		Integer batchId =50;
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		batchDTO reqDTO = new batchDTO(50,"SpringTesting",null,"nonActive",6,timestamp,timestamp,7);
		
		programEntity existingProgEntity = new programEntity(11,"devOPS",null, "active",timestamp,timestamp,null);
		batchEntity exisitngBatchEntity= new batchEntity(50,"DesignPatterns","new Course", "Active",6,timestamp, timestamp,existingProgEntity );
		
		
		programEntity modifiedProgramEntity = new programEntity(7,"Jenkins",null, "active",timestamp,timestamp,null);
		batchEntity modifiedEntity = new batchEntity(50,"SpringTesting",null, "nonActive",6,timestamp, timestamp,modifiedProgramEntity );
		
		batchDTO BatchDTO= new batchDTO(50,"SpringTesting",null,"nonActive",6,timestamp,timestamp,7);
		
		
		Mockito.when(batchRepo.findById(batchId)).thenReturn(Optional.of(exisitngBatchEntity));
		
		Mockito.when(batchRepo.getById(batchId)).thenReturn(exisitngBatchEntity);
		
		Mockito.when(progRepo.getById(modifiedProgramEntity.getProgramId())).thenReturn(modifiedProgramEntity);
		
		Mockito.when(batchRepo.save(exisitngBatchEntity)).thenReturn(modifiedEntity);
		
		Mockito.when(batchMap.toBatchDTO(modifiedEntity)).thenReturn(BatchDTO);
		
		
		//when or Action
		batchDTO fetchedFromService = batchService.updateBatchById(batchId,reqDTO);
		
		//then or Assert
		assertThat(fetchedFromService).isNotNull();
		assertThat(fetchedFromService.getBatch_description()).isEqualTo(reqDTO.getBatch_description());
		assertThat(fetchedFromService.getBatch_name()).isEqualTo(reqDTO.getBatch_name());
	    //assertThat(fetchedFromService).isSameAs(new BatchDTO);
		//assertThat(reqDTO).isSameAs(fetchedFromService);
		assertThat(BatchDTO).isNotNull();
		
		//verify(batchRepo, times(3));
		//verify(batchRepo).getById(batchId);
		verify(batchRepo).findById(batchId);
		verify(progRepo).getById(exisitngBatchEntity.getProgramEntity_batch().getProgramId());
		
		//verify(batchRepo).save(modifiedEntity);
		
		verify(batchMap).toBatchDTO(modifiedEntity);
		
	
	}
	
	//getBatchEntitesByProgramId(Integer batch_program_id)
	@Test
	void testgetBatchEntitesByProgramId() throws ResourceNotFoundException, DuplicateResourceFound
	{
		//Given
		Integer batch_programId =6;
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		batchEntity batchEntity1= new batchEntity(50,"DesignPatterns","new Course", "Active",6,timestamp, timestamp, null);
		
		batchEntity batchEntity2 = new batchEntity(51,"SpringMVC","new Course", "Active",6,timestamp, timestamp, null);
		
		batchEntity batchEntity3 =new batchEntity(53,"SpringSecurity","new Course", "Active",6,timestamp, timestamp, null);
		
		List<batchEntity> listBatchEntityWithProgramId = new ArrayList<>();
		listBatchEntityWithProgramId.add(batchEntity1);
		listBatchEntityWithProgramId.add(batchEntity2);
		listBatchEntityWithProgramId.add(batchEntity3);
		
		
		batchDTO BatchDTO1= new batchDTO(50,"DesignPatterns",null,"nonActive",6,timestamp,timestamp,null);
		batchDTO BatchDTO2= new batchDTO(51,"SpringMVC",null,"nonActive",6,timestamp,timestamp,null);
		batchDTO BatchDTO3= new batchDTO(53,"SpringSecurity",null,"nonActive",6,timestamp,timestamp,null);
		
		List<batchDTO> listBatchDTOSWithProgramId = new ArrayList<>();
		listBatchDTOSWithProgramId.add(BatchDTO1);
		listBatchDTOSWithProgramId.add(BatchDTO2);
		listBatchDTOSWithProgramId.add(BatchDTO3);
		
		
		Mockito.when(batchRepo.existsById(batch_programId)).thenReturn(true);
		
		Mockito.when(batchRepo.findAllByBatch_ProgramId(batch_programId)).thenReturn(listBatchEntityWithProgramId);
		
		Mockito.when(batchMap.toBatchDTOList(listBatchEntityWithProgramId)).thenReturn(listBatchDTOSWithProgramId);
		
		//when or Action
		List<batchDTO> fetchedFromService = batchService.getBatchEntitesByProgramId(batch_programId);
		
		//then or Assert
		assertThat(fetchedFromService).isNotNull();
		assertThat(fetchedFromService.size()).isEqualTo(listBatchDTOSWithProgramId.size());
		assertThat(fetchedFromService).isEqualTo(listBatchDTOSWithProgramId);
	    //assertThat(fetchedFromService).isSameAs(new BatchDTO);
		//assertThat(reqDTO).isSameAs(fetchedFromService);
				
		verify(batchRepo).existsById(batch_programId);
		verify(batchRepo).findAllByBatch_ProgramId(batch_programId);
		
	
	}
	//deleteByBatchId
	@Test
	void testDeleteByBatchId() throws ResourceNotFoundException, DuplicateResourceFound
	{
		//Given
		Integer batchId =5;
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		batchEntity deletingEntity = new batchEntity(5,"SpringTesting",null, "nonActive",6,timestamp, timestamp, null);
		
		Mockito.when(batchRepo.existsById(batchId)).thenReturn(true);
		
		//Mockito.when(batchRepo.deleteById(batchId)).thenReturn(true);
		
		//Mockito.when(batchRepo.delete(deletingEntity)).then();
		
		//when or Action
		boolean value =batchService.deleteByBatchId(batchId);
		
		//then or Assert
		assertThat(value).isEqualTo(true);
		verify(batchRepo, times(1)).existsById(batchId);
		verify(batchRepo).deleteById(batchId);
		
	
	}
	
	//deleteByBatchName
	@Test
	void testDeleteByBatchName() throws ResourceNotFoundException, DuplicateResourceFound
	{
		//Given
		String batchName ="Spring MVC";
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		
		batchEntity toBeDeletedEntity= new batchEntity(50,"Spring MVC","new Course", "Active",6,timestamp, timestamp, null);
		
		List<batchEntity> ListOfEntitesWithBatchName = new ArrayList<>();
		ListOfEntitesWithBatchName.add(toBeDeletedEntity);
		
		Mockito.when(batchRepo.findByBatchName(batchName)).thenReturn(ListOfEntitesWithBatchName);
		
		//Mockito.when(batchRepo.delete(deletingEntity)).then();
		
		//when or Action
		boolean value =batchService.deleteBybatchName(batchName);
		
		//then or Assert
		assertThat(value).isEqualTo(true);
		verify(batchRepo,times(1)).findByBatchName(batchName);
		verify(batchRepo).delete(toBeDeletedEntity);
		
	}

}
