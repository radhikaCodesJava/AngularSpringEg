package com.demo.assignment.utiltests;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.demo.assignment.entity.batchEntity;

import com.demo.assignment.model.batchDTO;
import com.demo.assignment.util.batchMapper;

public class BatchMapperTests {

	
	@Test
	public void shouldMapBatchToDto() {
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
	   //given
	    batchEntity batchObj = new batchEntity(5,"DesignPatterns",null, "nonActive",6, timestamp, timestamp, null);
	 
	   //when
	    batchDTO batchDto = batchMapper.INSTANCE.toBatchDTO( batchObj );
	 
	   //then
	    assertThat( batchDto ).isNotNull();
	    assertThat( batchDto.getBatch_name()).isEqualTo( "DesignPatterns" );
	    assertThat( batchDto.getBatch_status() ).isEqualTo( "nonActive" );
	    
	}
}
