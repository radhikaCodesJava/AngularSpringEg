package com.demo.assignment.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.demo.assignment.entity.programEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class batchDTO {
	@NotNull(message="batchID should not be null")
	private Integer batch_id;
	@NotNull(message="batchname should not be null")
	private String batch_name;
	private String batch_description;
	private String batch_status;
	private int batch_num_classes;
	private Timestamp creation_time;
	private Timestamp last_modified_time;
	private programEntity batch_program_id;
}
