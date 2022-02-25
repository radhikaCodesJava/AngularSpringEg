package com.demo.assignment.repotests;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.entity.programEntity;
import com.demo.assignment.repo.programRepository;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ProgramRepoTests {

	@Autowired
	private programRepository progRepo;
	
	@DisplayName("junit test for createProgram operation")
	@Test
	@Order(1)
	@Rollback(value=false)
		public void testCreateProgram()
	{
		//Given -setup
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		
		//List<batchEntity> listOfBatchIds
		/*List<batchEntity> batchesList = Arrays.asList(
                new batchEntity(23,"02","data-science","Active",5,null,null,4),
                new batchEntity(24,"06","springBoot","Active",10,null,null,6),
                new batchEntity(25,"07","Angular","NON-Active",4,null,null,7)
        );*/
		
		//when-action or behaviour that we are going to test
		programEntity newprog= new programEntity(1,"Ruby",null,"nonActive", timestamp,timestamp,null);
		
		programEntity saveEntity=progRepo.save(newprog);
		
		//then- verify the output
		assertNotNull(saveEntity);
		assertThat(newprog).isNotNull();
		
	assertThat(saveEntity.getProgramId()).isEqualTo(newprog.getProgramId());
	
	}
	
	//junit test for get list of programs from repo
	@DisplayName("junit test for getAllProgramList operation")
	@Test
	@Order(2)
	@Rollback(value=false)
		public void testGetAllPrograms()
	{
		//Given -setup
		/*LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		
			
		programEntity newprog= new programEntity(1,"aws",null,"nonActive", timestamp,timestamp,null);
		programEntity newprog1= new programEntity(5,"Dart&Flutter",null,"active",timestamp, timestamp, null);
		
		progRepo.save(newprog);
		progRepo.save(newprog1);*/
		
		//when-action or behaviour that we are going to test
		List<programEntity> listOfProgEntites=progRepo.findAll();
		
		//then- verify the output
		assertNotNull(listOfProgEntites);
		assertThat(listOfProgEntites.size()).isGreaterThan(0);
	}
	
	//junit test for get program by programId from repo
	@DisplayName("junit test for get program by id")
	@Test
	@Order(3)
	@Rollback(value=false)
	public void testGetProgramById()
	{
		//Given-setup
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		programEntity newProg= new programEntity(7,"Django",null, "nonActive",timestamp, timestamp, null);
		progRepo.save(newProg);
		//when-action or behaviour 
		programEntity progEntity= progRepo.getById(newProg.getProgramId());
		
		//then-verify
		assertNotNull(progEntity);
		assertThat(progEntity.getProgramId()).isEqualTo(newProg.getProgramId());
	}
	
	//junit test for get program by programName from repo
	@DisplayName("junit test for get program(unique) by prograName")
	public void testFindProgramByName() {
	//Given-setup
		LocalDateTime now=LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		programEntity newProg= new programEntity(18,"Docker",null, "nonActive",timestamp, timestamp, null);
		progRepo.save(newProg);
		//when-action or bhehaviour
		List<programEntity> progEntityList=progRepo.findByProgramName(newProg.getProgram_name());
		
		//then-verify
		assertNotNull(progEntityList);
		assertThat(progEntityList.size()).isGreaterThan(0);
		
		//assertThat(progEntity.getProgram_name()).isEqualTo(newProg.getProgram_name());
	}
	//junit test for update program by programid in repo
	public void testUpdateByProgramId() {
		
	}
	
	//junit test for update program by programName in repo
	public void testUpdateByProgramName() {
		
	}
	//junit test for delete program by program Id from repo
	public void testDeleteByProgramId() {
		
	}
	//junit test for delete program by programName from repo
	public void testDeleteByProgramName() {
		
	}
}
