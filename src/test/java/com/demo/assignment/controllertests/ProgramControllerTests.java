	package com.demo.assignment.controllertests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;

//import org.assertj.core.error.ShouldHaveSameSizeAs;
import org.hamcrest.core.IsEqual;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.demo.assignment.controller.programController;
import com.demo.assignment.entity.programEntity;
//import com.demo.assignment.exception.ResourceNotFoundException;
import com.demo.assignment.model.programDTO;
//import com.demo.assignment.repo.programRepository;
import com.demo.assignment.service.programService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

//@AutoConfigureMockMvc
//@ContextConfiguration(classes = {programController.class, programService.class})
@WebMvcTest(programController.class)
//@WithMockUser(username="myApp@123",password = "myApp@Sucess")
	@WithMockUser
public class ProgramControllerTests {
	
	@Value("${server.port}")
	private String port;
	
	//@Value("${spring.security.user.name}")
	//private String userName;
	
	//@Value("${spring.security.user.password}")
	//private String passWord;
	
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private programService progService;
	
	
	@Autowired
	private ObjectMapper obj;
	
	List<programDTO> ListofDTOs;
	
	@BeforeEach
    public void setup(){
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
				
       programDTO newProgDTO1= new programDTO(5,"DesignPatterns",null, "nonActive",timestamp, timestamp, null);
		
       programDTO newProgDTO2= new programDTO(20,"RestAssuredAPITesting",null,"nonActive",timestamp,timestamp,null);
		ListofDTOs = new ArrayList<>();
		
		ListofDTOs.add(newProgDTO1);
		ListofDTOs.add(newProgDTO2);
		
		// Print the programname from the list....
				System.out.println("using for each loop");
		        for(programDTO dto : ListofDTOs) {
		            System.out.println(dto.getProgram_name());
		        }

		        // Or like this...
		        System.out.println("using normal way");
		        for(int i = 0; i < ListofDTOs.size(); i++) {
		            System.out.println(ListofDTOs.get(i).getProgram_name());
		           }
		        
		       //or with java 8 and method reference
		        System.out.println("using java 8");
		        ListofDTOs.forEach(System.out::println);
		        
		        //or just simply
		        System.out.println(ListofDTOs);
				
		        System.out.println("list of DTOs :"+ ListofDTOs.get(1).getProgram_name());
		        
		        System.out.println("list of DTOs :"+ ListofDTOs.toString());
		
	   }
	
	@Test
	
	public void testGetPrograms()throws Exception {
		
		//System.out.println("userName :"+userName +"\n"+"password :"+passWord );
		
		//Given
		Mockito.when(progService.getAllPrograms()).thenReturn(ListofDTOs);
		//above line can be written using BDDMockito too.
		//given(progService.getAllPrograms()).willReturn(ListofDTOs);
		
		//when
		String url="http://localhost:"+port+"/allPrograms";
		System.out.println("url is:"+url);
				
		//way 1
		//ResultActions result=
			MvcResult result=
							mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)
									                 //.content(ObjectMapper.writeValueAsString())
	                                                   )
									// .andDo(MockMvcResultHandlers.print())  -not compulsory
									.andExpect(status().isOk())
									//.andExpect(jsonPath("$",hasSize(2))) //- signer information at runtime
				                	.andExpect(MockMvcResultMatchers.jsonPath("$[0].program_id").value(ListofDTOs.get(0).getProgram_id()))
								  	.andExpect(jsonPath("$[0].program_name",is(ListofDTOs.get(0).getProgram_name())))
									.andReturn();
		//way 2
		/*mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
        .andDo(mvcResult -> {
            String JsonResponseAsString = mvcResult.getResponse().getContentAsString();
            String a = com.jayway.jsonpath.JsonPath.parse(JsonResponseAsString).read("$[0].program_name").toString();
            Assert.isTrue(a.equals(ListofDTOs.get(0).getProgram_name()),"Both must be equal");
        });*/
		
		//String JsonResponseAsStringvalue= result.getResponse().getContentAsString();
		//String a = com.jayway.jsonpath.JsonPath.parse(JsonResponseAsStringvalue).read("$[0].program_name").toString();
		
		/*
		 * mockMvc.perform(get(url))
            .andDo(mvcResult -> {
                String json = mvcResult.getResponse().getContentAsString();
                String a = JsonPath.parse(json).read("$.firstItem").toString();
                String b = JsonPath.parse(json).read("$.location[0].path").toString();
                Assert.isTrue(a.equals(b),"firstItem is different from location[0].path");
            });
		 */
		
		//.andExpect(content().json("{'message':'ok'}")
		/*
		 * .andExpect(jsonPath("$.id", is(this.bookmarkList.get(0).getId().intValue())))
    	   .andExpect(jsonPath("$.uri", is("http://bookmark.com/1/" + userName)))
    	   .andExpect(jsonPath("$.description", is("A description")));
		 * 
		 */
		
		
		//then
		//System.out.println(result.getResponse().getContentLength());
			
		
		
			assertNotNull(result.getResponse().getContentAsString());
			
			assertThat(200).isEqualTo(result.getResponse().getStatus());
			
			assertThat("application/json").isEqualTo(result.getResponse().getContentType());
			
			//Assert.isTrue(a.equals(ListofDTOs.get(0).getProgram_name()),"Both must be equal");
			
	}	
	
	//GetProgramByIdTest
	//@GetMapping(path="programs/{programId}", produces = "application/json")  
	@Test
	//@WithMockUser(username="myApp@123",password = "myApp@Sucess")
	@WithMockUser
	public void testGetProgramById()throws Exception {
		
		//Given
			programDTO programfetchbyId = ListofDTOs.get(1);
				Mockito.when(progService.getProgramsById(programfetchbyId.getProgram_id())).thenReturn(programfetchbyId);
				//above line can be written using BDDMockito too.
				//given(progService.getProgramsById(programfetchbyId.getProgram_id()).willReturn(programfetchbyId);
				
				//when
				String url="http://localhost:"+port+"/programs/"+programfetchbyId.getProgram_id();
				System.out.println("url is:"+url);
						
				//way 1
				//ResultActions result=
					MvcResult result=
									mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
											.andDo(MockMvcResultHandlers.print())  
											.andExpect(status().isOk())
											.andExpect(MockMvcResultMatchers.jsonPath("$.program_id").value(programfetchbyId.getProgram_id()))
										  	.andExpect(jsonPath("program_name",is(programfetchbyId.getProgram_name())))
											.andReturn();
				//way 2
				/*mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
		        .andDo(mvcResult -> {
		            String JsonResponseAsString = mvcResult.getResponse().getContentAsString();
		            String a = com.jayway.jsonpath.JsonPath.parse(JsonResponseAsString).read("$.program_name").toString();
		            Assert.isTrue(a.equals(programfetchbyId.getProgram_name()),"Both must be equal");
		        });*/
				
				//String JsonResponseAsStringvalue= result.getResponse().getContentAsString();
				//String a = com.jayway.jsonpath.JsonPath.parse(JsonResponseAsStringvalue).read("$.program_name").toString();
				
				    assertNotNull(result.getResponse().getContentAsString());
					
					assertThat(200).isEqualTo(result.getResponse().getStatus());
					
					assertThat("application/json").isEqualTo(result.getResponse().getContentType());
					
					//Assert.isTrue(a.equals(programfetchbyId.getProgram_name()),"Both must be equal");
		
	}
	
	//saveProgramTest
	//@PostMapping(path="/saveprogram",consumes = "application/json", produces = "application/json") 
	@Test
	//@WithMockUser(username="myApp@123",password = "myApp@Sucess")
	@WithMockUser
	public void testSaveProgram()throws Exception {
	//Given	
		//new program details:
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		programDTO newProgDTO3= new programDTO(10,"IntegrationTesting",null, "nonActive",timestamp, timestamp, null);
		
		Mockito.when(progService.createAndSaveProgram(newProgDTO3)).thenReturn(newProgDTO3);
		
		
		//when
		String url="http://localhost:"+port+"/saveprogram";
		System.out.println("url is:"+url);
				
		//way 1
		//ResultActions result=
			MvcResult result=
							mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
									                 .content(obj.writeValueAsString(newProgDTO3)) )
									.andDo(MockMvcResultHandlers.print())  
									.andExpect(status().isCreated())
									//.andExpect(MockMvcResultMatchers.jsonPath("$.program_id").value(newProgDTO3.getProgram_id()))
								  	//.andExpect(jsonPath("program_name",is(newProgDTO3.getProgram_name())))
									.andReturn();
		//way 2
		/*mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)
		 *                        .content(obj.writeValueAsString(newProgDTO3)))
        .andDo(mvcResult -> {
            String JsonResponseAsString = mvcResult.getResponse().getContentAsString();
            String a = com.jayway.jsonpath.JsonPath.parse(JsonResponseAsString).read("$.program_name").toString();
            Assert.isTrue(a.equals(programfetchbyId.getProgram_name()),"Both must be equal");
        });*/
		
		String JsonResponseAsStringvalue= result.getResponse().getContentAsString();
		System.out.println("JsonResponseAsStringvalue"+JsonResponseAsStringvalue);
		//String a = com.jayway.jsonpath.JsonPath.parse(JsonResponseAsStringvalue).read("$.program_name").toString();
		
		    assertNotNull(result.getResponse().getContentAsString());
			
			assertThat(201).isEqualTo(result.getResponse().getStatus());
			
			//assertThat("application/json").isEqualTo(result.getResponse().getContentType());
			
			//Assert.isTrue(a.equals(programfetchbyId.getProgram_name()),"Both must be equal");
	}
	
	//updateProgramById
	//@PutMapping(path="/putprogram/{programId}", consumes = "application/json", produces = "application/json")
	@Test
	//@WithMockUser(username="myApp@123",password = "myApp@Sucess")
	@WithMockUser
	public void testUpdateProgramById()throws Exception {
		
	}
	
	//updateProgramByName
	//@PutMapping(path="/program/{programName}", consumes = "application/json", produces = "application/json")  
	@Test
	//@WithMockUser(username="myApp@123",password = "myApp@Sucess")
	@WithMockUser
	public void testUpdateProgramByName()throws Exception {
		
	}
	
	//deleteProgramById
	//@DeleteMapping(path="/deletebyprogid/{programId}",produces = "application/json") 
	@Test
	//@WithMockUser(username="myApp@123",password = "myApp@Sucess")
	@WithMockUser
	public void testDeleteProgramById()throws Exception {
		
	}
	
	//deleteProgramByName
	//@DeleteMapping(path="/deletebyprogname/{programName}",produces = "application/json")  
	@Test
	//@WithMockUser(username="myApp@123",password = "myApp@Sucess")
	@WithMockUser
	public void testDeleteProgramByName()throws Exception {
		
	}

}

