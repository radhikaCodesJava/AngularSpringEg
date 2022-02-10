package com.demo.assignment.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name="tbl_lms_program")
public class programEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public programEntity(@NonNull Integer program_id, @NonNull String program_name, @NonNull String program_description,
			@NonNull String program_status, @NonNull Timestamp creation_time, @NonNull Timestamp last_mod_time) {
		super();
		this.program_id = program_id;
		this.program_name = program_name;
		this.program_description = program_description;
		this.program_status = program_status;
		this.creation_time = creation_time;
		this.last_mod_time = last_mod_time;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//, generator = "program_id_seq")
	@Column(name="program_id")//,nullable=false, unique=true, insertable=true, updatable=false)
	@NonNull
    private Integer program_id;

	@Column(name="program_name")//, nullable=false,unique=true, insertable=true, updatable=false)
	@NonNull
	private String program_name;

	@Column(name="program_description")
	@NonNull
    private String program_description;

	@Column(name="program_status")
	@NonNull
	private String program_status;

	@Column(name="creation_time")
	@NonNull
	private Timestamp creation_time;

	@Column(name="last_mod_time")
	@NonNull
	private Timestamp last_mod_time;

	
	@OneToMany(targetEntity = batchEntity.class, mappedBy="programEntity_batch", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<batchEntity> listOfBatchIds;// =new ArrayList<batchEntity>();

	

}
