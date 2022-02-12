package com.demo.assignment.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="tbl_lms_batch")
public class batchEntity implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_Entity_id")
	@SequenceGenerator(name="batch_Id", sequenceName = "batch_table_id_seq", allocationSize = 1)
	@Column( name = "batch_id" )
	@NonNull
	private Integer batch_id;
	
	
	
	@Column( name = "batch_name" )
	@NonNull
	private String batch_name;
	
	@Column( name = "batch_description" )
	private String batch_description;
	
    //@Column(name="batch_program_id")
    //private Integer batch_program_id;
	
	@Column( name = "batch_status" )
	private String batch_status;
	
	@Column(name="batch_no_of_classes")
	private Integer batch_num_classes;
	
	@Column(name="createion_time")
	private Timestamp creation_time;
	
	@Column(name="last_mod_time")
	private Timestamp last_modified_time;
	
	@ManyToOne(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="batch_program_id", insertable=false, updatable=false,referencedColumnName = "program_id", unique = true,nullable=false)
	@JsonIgnore
	private programEntity programEntity_batch;
}
