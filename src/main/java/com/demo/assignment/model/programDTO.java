package com.demo.assignment.model;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.demo.assignment.entity.batchEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class programDTO {
	//@NotNull(message="programID should not be null")
	private Integer program_id;
	//@NotNull(message="programName should not be empty")
	private String program_name;
	private String program_description;
	private String program_status;
	private Timestamp creation_time;
	private Timestamp last_mod_time;
	private List<batchEntity> listOfBatchIds;
}
