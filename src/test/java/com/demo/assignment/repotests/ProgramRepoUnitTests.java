package com.demo.assignment.repotests;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.assignment.ProgramBatchDemoApplication;
import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.entity.programEntity;
import com.demo.assignment.repo.programRepository;



//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@ContextConfiguration(classes=ProgramBatchDemoApplication.class)
public class ProgramRepoUnitTests {

	@Autowired
	private programRepository progRepo;
	
	private programEntity newProg;
	
	@BeforeEach
    public void setup(){
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		newProg= new programEntity(7,"Django",null, "nonActive",timestamp, timestamp, null);
	   }
	
	/*@AfterEach
	public void tearDown() {
		
	}*/
	
	
	
	@DisplayName("junit test for createProgram operation")
	@Test
	@Order(1)
	//@Rollback(value=false)
		public void testCreateProgram()
	{
		//Given -setup
		//when-action or behaviour that we are going to test
		programEntity saveEntity=progRepo.save(newProg);
		
		//then- verify the output
		assertNotNull(saveEntity);
		assertThat(newProg).isNotNull();
		
	assertThat(saveEntity.getProgramId()).isEqualTo(saveEntity.getProgramId());
	
	}
	
	//junit test for get list of programs from repo
	@DisplayName("junit test for getAllProgramList operation")
	@Test
	@Order(2)
	public void testGetAllPrograms()
	{
		//Given -setup
		programEntity initialSavedEntity=progRepo.save(newProg);
				
		//when-action or behaviour that we are going to test
		List<programEntity> listOfProgEntites=progRepo.findAll();
		
		//then- verify the output
		assertNotNull(listOfProgEntites);
		assertThat(listOfProgEntites.size()).isGreaterThan(0);
		
		Assertions.assertThat(listOfProgEntites).extracting(programEntity::getProgram_name).containsAnyOf("Django");

	}
	
	//junit test for get program by programId from repo
	@DisplayName("junit test for get program by id")
	@Test
	@Order(3)
	public void testGetProgramById()
	{
		//Given-setup
		programEntity intialSavedEntity=progRepo.save(newProg);
		
		//when-action or behaviour 
		programEntity progEntity= progRepo.getById(intialSavedEntity.getProgramId());
		
		//then-verify
		assertNotNull(progEntity);
		assertThat(progEntity.getProgramId()).isEqualTo(intialSavedEntity.getProgramId());
	}
	
	//junit test for get program by programName from repo
	@DisplayName("junit test for get program(unique) by prograName")
	@Test
	@Order(4)
	public void testFindProgramByName() 
	{
	 //Given-setup
		programEntity initialSavedEntity=progRepo.save(newProg);
		
		//when-action or bhehaviour
		List<programEntity> progEntityList=progRepo.findByProgramName(initialSavedEntity.getProgram_name());
		programEntity foundprogEntity =null;
		for(programEntity rec:progEntityList) {
			 if( rec.getProgram_name().equalsIgnoreCase(initialSavedEntity.getProgram_name())) {
			   foundprogEntity =rec;
			}
		  }
		
		//then-verify
				assertNotNull(progEntityList);
				assertThat(progEntityList.size()).isGreaterThan(0);
				assertNotNull(foundprogEntity);
		assertThat(foundprogEntity.getProgram_name()).isEqualTo(newProg.getProgram_name());
	}
	
	//junit test for update program by programid in repo
	@DisplayName("junit test for updating a rec in repo with progId")
	@Test
	@Order(5)
	//@Rollback(value=false)
	public void testUpdateByProgramId() {
		//Given-setup
		programEntity intialSavedEntity=progRepo.save(newProg);
		
		//when-action or behaviour
		programEntity progEntity= progRepo.getById(intialSavedEntity.getProgramId());
		progEntity.setProgram_description("old course");
		progEntity.setProgram_status("active");
		programEntity modifiedSavedEntity=progRepo.save(progEntity);
		
		//then-verify
		assertNotNull(modifiedSavedEntity);
		assertThat(modifiedSavedEntity.getProgram_description()).isEqualTo(progEntity.getProgram_description());
		assertThat(modifiedSavedEntity.getProgram_status()).isEqualTo(progEntity.getProgram_status());
	}
	
	//junit test for update program by programName in repo
	@Test
	@Order(6)
	//@Rollback(value=false)
	public void testUpdateByProgramName() {
		//Given-setup
		programEntity initialSavedProgramEntity =progRepo.save(newProg);
		
		//when-action or behaviour
		List<programEntity> progEntityList=progRepo.findByProgramName(initialSavedProgramEntity.getProgram_name());
		programEntity foundprogEntity =null;
		for(programEntity rec:progEntityList) {
			 if( rec.getProgram_name().equalsIgnoreCase(newProg.getProgram_name())) {
			   foundprogEntity =rec;
			}
		  }
		foundprogEntity.setProgram_description("old course");
		foundprogEntity.setProgram_status("active");
		programEntity modifiedSavedEntity= progRepo.save(foundprogEntity);
		
		//then-verify
		assertNotNull(progEntityList);
		assertNotNull(foundprogEntity);
		assertNotNull(modifiedSavedEntity);
		assertThat(modifiedSavedEntity.getProgram_description()).isEqualTo(foundprogEntity.getProgram_description());
		assertThat(modifiedSavedEntity.getProgram_status()).isEqualTo(foundprogEntity.getProgram_status());
	}
	
	//junit test for delete program by program Id from repo
	@Test
	@Order(7)
	///@Rollback(value=false)
	public void testDeleteByProgramId() {
		//Given-setup
	  programEntity initialSavedProgramEntity =progRepo.save(newProg);
		
	  //when-action or behaviour
	  programEntity progEntity = progRepo.getById(initialSavedProgramEntity.getProgramId());
	  progRepo.deleteById(progEntity.getProgramId());
	  Optional<programEntity> programOptional= progRepo.findById(progEntity.getProgramId());
	  //then-verify
	  assertThat(programOptional).isEmpty();
	}
	
	//junit test for delete program by programName from repo
	@Test
	@Order(8)
	//@Rollback(value=false)
	public void testDeleteByProgramName() {
		//Given-setup
		programEntity initialSavedProgramEntity =progRepo.save(newProg);
	  
	  //when-action or behaviour
	   List<programEntity> progEntityList=progRepo.findByProgramName(initialSavedProgramEntity.getProgram_name());
		programEntity foundprogEntity =null;
		for(programEntity rec:progEntityList) {
			 if( rec.getProgram_name().equalsIgnoreCase(newProg.getProgram_name())) {
			   foundprogEntity =rec;
			}
		  }
		 progRepo.delete(foundprogEntity);
		 Optional<programEntity> programOptional = progRepo.findById(foundprogEntity.getProgramId());
		 
		 //then-verify
		 assertThat(programOptional).isEmpty();
		
	}
}
