package com.demo.assignment.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tbl_lms_program")
public class programEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "program_id_seq")
	@Column(name="program_id",nullable=false, unique=true, insertable=true, updatable=false)
	@NonNull
    private Integer program_id;

	@Column(name="program_name", nullable=false,unique=true, insertable=true, updatable=false)
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

	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "programDetails")
	//private List<batchEntity> batchIds;

}
