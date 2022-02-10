package com.demo.assignment.util;

import java.util.List;

import org.mapstruct.Mapper;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.model.batchDTO;

@Mapper(componentModel = "spring")//, uses=batchServiceImpl.class)
public interface batchMapper {
	batchDTO toBatchDTO(batchEntity savedEntity);
	
	batchEntity toBatchEntity(batchDTO batchDTO);
	 
   	List<batchDTO> toBatchDTOList(List<batchEntity> batcgEntities);
	 
	 List<batchEntity> toBatchEntityList(List<batchDTO> batchDTOs);

}
