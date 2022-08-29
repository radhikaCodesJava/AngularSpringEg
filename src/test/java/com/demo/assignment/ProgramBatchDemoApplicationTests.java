package com.demo.assignment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.assignment.controller.batchController;
import com.demo.assignment.controller.programController;

@ExtendWith(SpringExtension.class)
@SpringBootTest//(classes=ProgramBatchDemoApplicationTests.class)
class ProgramBatchDemoApplicationTests {

//@Autowired
//programController pc;

//@Autowired
//batchController bc;

	@Test
	void contextLoads() {
		//Testing if Application Loads Correctly
		//Assertions.assertThat(pc).isNot(null);
		//Assertions.assertThat(bc).isNot(null);
		
	}

}
