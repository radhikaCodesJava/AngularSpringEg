package com.demo.assignment.util;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.demo.assignment.entity.programEntity;
import com.demo.assignment.model.programDTO;

@Mapper(componentModel = "spring", implementationPackage = "com.demo.assignment.util")
public interface programMapper {
	
	programMapper INSTANCE = Mappers.getMapper(programMapper.class);
	
	@Mapping(target="program_id", source="programId")
programDTO toProgramDTO(programEntity savedEntity);
	
	@InheritInverseConfiguration
	programEntity toProgramEntity(programDTO progDTO);
	 
   	List<programDTO> toProgramDTOList(List<programEntity> programEntities);
	 
	 List<programEntity> toPogramEntityList(List<programDTO> programDTOs);

}
