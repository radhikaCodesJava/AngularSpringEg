package com.demo.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.assignment.entity.programEntity;

@Repository
public interface programRepository extends JpaRepository<programEntity, Integer> {

}
