package com.demo.assignment.repotests;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
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
	
	
	@DisplayName("junit test for getAllProgramList operation")
	@Test
	@Rollback(value=false)
		public void testGetAllPrograms()
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
}
