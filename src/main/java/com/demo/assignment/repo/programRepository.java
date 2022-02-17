package com.demo.assignment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.assignment.entity.programEntity;

@Repository
public interface programRepository extends JpaRepository<programEntity, Integer> {
	@Query(value = "SELECT * FROM tbl_lms_program WHERE program_Name = ?", nativeQuery = true)
	List<programEntity> findByProgramName ( String programName);

}
