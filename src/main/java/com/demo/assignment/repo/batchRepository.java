package com.demo.assignment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.assignment.entity.batchEntity;


@Repository
public interface batchRepository extends JpaRepository<batchEntity, Integer> {
	@Query(value = "SELECT * FROM tbl_lms_batch WHERE batch_name = ?1 and batch_program_id = ?2", nativeQuery = true)
	List<batchEntity> findByBatch_nameAndBatch_program_id ( String batch_name, Integer batch_program_id);
	
	@Query(value = "SELECT * FROM tbl_lms_batch WHERE batch_Name = ?", nativeQuery = true)
	List<batchEntity> findByBatchName ( String batchName);
	
	@Query(value = "SELECT * FROM tbl_lms_batch WHERE batch_program_id = ?", nativeQuery = true)
	List<batchEntity> findAllByBatch_ProgramId( Integer batch_program_id );
}
