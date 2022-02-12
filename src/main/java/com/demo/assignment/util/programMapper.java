package com.demo.assignment.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.demo.assignment.entity.programEntity;
import com.demo.assignment.model.batchDTO;
import com.demo.assignment.model.programDTO;

@Mapper(componentModel = "spring", implementationPackage = "com.demo.assignment.util")
public interface programMapper {
	
	programMapper INSTANCE = Mappers.getMapper(programMapper.class);
	
programDTO toProgramDTO(programEntity savedEntity);
	
	programEntity toProgramEntity(programDTO progDTO);
	 
   	List<programDTO> toProgramDTOList(List<programEntity> programEntities);
	 
	 List<programEntity> toBatchEntityList(List<programDTO> programDTOs);

}
