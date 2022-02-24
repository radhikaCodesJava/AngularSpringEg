package com.demo.assignment.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor

@Entity
@Table(name="tbl_lms_program")
public class programEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public programEntity(Integer program_id, String program_name, String program_description,
			String program_status, Timestamp creation_time,  Timestamp last_mod_time) {
		super();
		this.programId = program_id;
		this.program_name = program_name;
		this.program_description = program_description;
		this.program_status = program_status;
		this.creation_time = creation_time;
		this.last_mod_time = last_mod_time;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "program_id_seq")
	@SequenceGenerator(name="program_id_seq", sequenceName = "tbl_lms_program_program_id_seq", allocationSize = 1)
	@Column(name="program_id")//,nullable=false, unique=true, insertable=true, updatable=false)
	private Integer programId;

	@Column(name="program_name")//, nullable=false,unique=true, insertable=true, updatable=false)
	private String program_name;

	@Column(name="program_description")
	private String program_description;

	@Column(name="program_status")
	private String program_status;

	@Column(name="creation_time")
	private Timestamp creation_time;

	@Column(name="last_mod_time")
	private Timestamp last_mod_time;

	
	@OneToMany(targetEntity = batchEntity.class, mappedBy="programEntity_batch")//, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	//@Embedded  
	private List<batchEntity> listOfBatchIds;// =new ArrayList<batchEntity>();

	

}
