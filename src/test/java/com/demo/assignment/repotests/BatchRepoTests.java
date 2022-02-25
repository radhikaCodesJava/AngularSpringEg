package com.demo.assignment.repotests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.demo.assignment.entity.programEntity;
import com.demo.assignment.repo.batchRepository;
import com.demo.assignment.entity.batchEntity;

    //@SpringBootTest
	@DataJpaTest
	//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@AutoConfigureTestDatabase(replace=Replace.NONE)
public class BatchRepoTests {
	
	@Autowired
		private TestEntityManager entityManager;
		
		@Autowired
		private batchRepository batchRepo;
		
		private programEntity newProg; 
		private batchEntity newBatch;
		
		@BeforeEach
	    public void setup(){
			LocalDateTime now= LocalDateTime.now();
			Timestamp timestamp= Timestamp.valueOf(now);
			//newProg= entityManager.persist(new programEntity(7,"Django",null, "nonActive",timestamp, timestamp, null));
			newProg = new programEntity(7,"Django",null, "nonActive",timestamp, timestamp, null);
			newBatch = new batchEntity(3,"03","SDET batch 15","active",10,timestamp,timestamp,newProg);
		   }
		
		@AfterEach
		//public void tearDown() {
		//	batchRepo.deleteById(newBatch.getBatch_id());
		//}
		
		
    //Test for createAndSaveProgram
		@DisplayName("junit test for createBatch operation in repo")
		@Test
		@Order(1)
		//@Rollback(value=false)
		public void testCreateAndSaveNewBatch() 
		{
			//Given -setup
			//when-action or behaviour that we are going to test
			batchEntity savedBatchEntity=batchRepo.save(newBatch);
			
			//then- verify the output
			assertNotNull(savedBatchEntity);
			assertThat(newBatch).isNotNull();
			
		assertThat(savedBatchEntity.getBatch_name()).isEqualTo(newBatch.getBatch_name());
		}
		
		
		//Test for size of getAllBatches
		@DisplayName("junit test for get all batches from repo")
		@Test
		@Order(2)
		public void testGetAllBatches(){
			
			//Given -setup
			batchEntity initialSaveBatch=batchRepo.save(newBatch);
			//when-action or behaviour that we are going to test
			List<batchEntity> savedBatchEntityList=batchRepo.findAll();
			
			//then- verify the output
			assertNotNull(savedBatchEntityList);
			assertThat(savedBatchEntityList.size()).isGreaterThan(0);
		}
		
		
		//Test for id check getBatchesById
		@DisplayName("junit test for get by id from repo")
		@Test
		@Order(3)
		//@Rollback(value=false)
		public void testGetBatchesById() {
			//Given -setup
			batchEntity initalSavedEntityBatch= batchRepo.save(newBatch);
			//when-action or behaviour that we are going to test
			batchEntity batchEntity= batchRepo.getById(initalSavedEntityBatch.getBatch_id());
			//then-verify the output
			assertNotNull(batchEntity);
			assertThat(batchEntity.getBatch_name()).isEqualTo(newBatch.getBatch_name());
		}
		
		//Test for findByBatch_nameAndBatch_program_id
		@DisplayName("junit test for get records from repo by batchName and programId")
		@Test
		@Order(4)
		public void testFindByBatchNameAndBatchProgramId()
		{
		//Given-setup
	batchEntity initialSavedBatch =batchRepo.save(newBatch);
		//when-action or behaviour that we are going to test
	List<batchEntity> batchEntityList= batchRepo.findByBatch_nameAndBatch_program_id(initialSavedBatch.getBatch_name(), initialSavedBatch.getProgramEntity_batch().getProgramId());
	batchEntity foundBatchEntity = null;
	for(batchEntity rec:batchEntityList) {
		 if( rec.getBatch_name().equalsIgnoreCase(newBatch.getBatch_name())) {
		   foundBatchEntity =rec;
		}
	  }
	
	    //then-verify the output	
			assertNotNull(batchEntityList);
			assertNotNull(foundBatchEntity);
			assertThat(foundBatchEntity.getBatch_name()).isEqualTo(newBatch.getBatch_name());
			assertThat(foundBatchEntity.getProgramEntity_batch().getProgramId()).isEqualTo(newBatch.getProgramEntity_batch().getProgramId());
		}
		
		//findByBatchName
		@DisplayName("junit test for find records with BatchName from repo")
		@Test
		@Order(5)
	   public void testFindByBatchName()
	   {
		//Given-setup
			batchEntity initialSavedBatchEntity=batchRepo.save(newBatch);
		//when-action or behaviour that we are going to test
			
			//when-action or bhehaviour
			List<batchEntity> batchEntityList=batchRepo.findByBatchName(initialSavedBatchEntity.getBatch_name());
			batchEntity foundbatchEntity =null;
			for(batchEntity rec:batchEntityList) {
				 if( rec.getBatch_name().equalsIgnoreCase(newBatch.getBatch_name())) {
				   foundbatchEntity =rec;
				}
			  }
			
			//then-verify
					assertNotNull(batchEntityList);
					assertThat(batchEntityList.size()).isGreaterThan(0);
					assertNotNull(foundbatchEntity);
			assertThat(foundbatchEntity.getBatch_name()).isEqualTo(newBatch.getBatch_name());
			
	   }
		
		
		//findAllByBatch_ProgramId  -can this be?orderby batch_program_Id
		@DisplayName("junit test for find records with Batch_Program_ID from repo")
		@Test
		@Order(5)
	   public void testFindByBatchProgramId()
	   {
		//Given-setup
			batchEntity initialSavedBatchEntity=batchRepo.save(newBatch);
		//when-action or behaviour that we are going to test
			
			//when-action or bhehaviour
			List<batchEntity> batchEntityList=batchRepo.findAllByBatch_ProgramId(initialSavedBatchEntity.getProgramEntity_batch().getProgramId());
			batchEntity foundbatchEntity =null;
			for(batchEntity rec:batchEntityList) {
				 if((rec.getProgramEntity_batch().getProgramId())==(newBatch.getProgramEntity_batch().getProgramId())) {
				   foundbatchEntity =rec;
				}
			  }
			
			//then-verify
					assertNotNull(batchEntityList);
					assertThat(batchEntityList.size()).isGreaterThan(0);
					assertNotNull(foundbatchEntity);
			assertThat(foundbatchEntity.getProgramEntity_batch().getProgramId()).isEqualTo(newBatch.getProgramEntity_batch().getProgramId());
			
	   }
		
		
			//Test for update based on batchId
		@DisplayName("junit test for updating rec with batchId in repo")
	    @Test
		@Order(6)
	    //@Rollback(value=false)
			public void testUpdateBatchById()
		{
			//Given-setup
			batchEntity intialSave=batchRepo.save(newBatch);
			
			//when-action or behaviour
			batchEntity batchEntity= batchRepo.getById(intialSave.getBatch_id());
			
			batchEntity.setBatch_description("addon to programmers and testers");
			batchEntity.setBatch_status("active");
			
			batchEntity modifiedSavedEntity=batchRepo.save(batchEntity);
			
			//then-verify
			assertNotNull(modifiedSavedEntity);
			assertThat(modifiedSavedEntity.getBatch_description()).isEqualTo(batchEntity.getBatch_description());
			assertThat(modifiedSavedEntity.getBatch_status()).isEqualTo(batchEntity.getBatch_status());
		}
	    
	    
			
			//delete by batchId
		@DisplayName("junit test for deleting rec with batchId")
	    @Test
	    @Order(7)
	   // @Rollback(value=false)
			public void testDeleteByBatchId() {
	    	//Given-setup
			batchEntity initialSavedEntity=batchRepo.save(newBatch);
			
			//when-action or behaviour
			batchEntity batchEntity= batchRepo.getById(initialSavedEntity.getBatch_id());
			batchRepo.deleteById(batchEntity.getBatch_id());
			
			Optional<batchEntity> batchOptional = batchRepo.findById(batchEntity.getBatch_id());
			
			  //then-verify
			 assertThat(batchOptional).isEmpty();			
		}
		
	}			
			