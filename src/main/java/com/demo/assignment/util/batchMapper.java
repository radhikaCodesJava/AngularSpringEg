package com.demo.assignment.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.model.batchDTO;


@Mapper(componentModel = "spring", implementationPackage = "com.demo.assignment.util")//, uses=batchServiceImpl.class)
public interface batchMapper {
	
	batchMapper INSTANCE = Mappers.getMapper(batchMapper.class);
	
	batchDTO toBatchDTO(batchEntity savedEntity);
	
	batchEntity toBatchEntity(batchDTO batchDTO);
	 
   	List<batchDTO> toBatchDTOList(List<batchEntity> batchEntities);
	 
	 List<batchEntity> toBatchEntityList(List<batchDTO> batchDTOs);

}
