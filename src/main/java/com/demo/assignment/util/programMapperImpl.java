package com.demo.assignment.util;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.entity.programEntity;
import com.demo.assignment.model.programDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-11T18:57:52-0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class programMapperImpl implements programMapper {

    @Override
    public programDTO toProgramDTO(programEntity savedEntity) {
        if ( savedEntity == null ) {
            return null;
        }

        programDTO programDTO = new programDTO();

        programDTO.setProgram_id( savedEntity.getProgram_id() );
        programDTO.setProgram_name( savedEntity.getProgram_name() );
        programDTO.setProgram_description( savedEntity.getProgram_description() );
        programDTO.setProgram_status( savedEntity.getProgram_status() );
        programDTO.setCreation_time( savedEntity.getCreation_time() );
        programDTO.setLast_mod_time( savedEntity.getLast_mod_time() );
        List<batchEntity> list = savedEntity.getListOfBatchIds();
        if ( list != null ) {
            programDTO.setListOfBatchIds( new ArrayList<batchEntity>( list ) );
        }

        return programDTO;
    }

    @Override
    public programEntity toProgramEntity(programDTO progDTO) {
        if ( progDTO == null ) {
            return null;
        }

        programEntity programEntity = new programEntity();

        programEntity.setProgram_id( progDTO.getProgram_id() );
        programEntity.setProgram_name( progDTO.getProgram_name() );
        programEntity.setProgram_description( progDTO.getProgram_description() );
        programEntity.setProgram_status( progDTO.getProgram_status() );
        programEntity.setCreation_time( progDTO.getCreation_time() );
        programEntity.setLast_mod_time( progDTO.getLast_mod_time() );
        List<batchEntity> list = progDTO.getListOfBatchIds();
        if ( list != null ) {
            programEntity.setListOfBatchIds( new ArrayList<batchEntity>( list ) );
        }

        return programEntity;
    }

    @Override
    public List<programDTO> toProgramDTOList(List<programEntity> programEntities) {
        if ( programEntities == null ) {
            return null;
        }

        List<programDTO> list = new ArrayList<programDTO>( programEntities.size() );
        for ( programEntity programEntity : programEntities ) {
            list.add( toProgramDTO( programEntity ) );
        }

        return list;
    }

    @Override
    public List<programEntity> toPogramEntityList(List<programDTO> programDTOs) {
        if ( programDTOs == null ) {
            return null;
        }

        List<programEntity> list = new ArrayList<programEntity>( programDTOs.size() );
        for ( programDTO programDTO : programDTOs ) {
            list.add( toProgramEntity( programDTO ) );
        }

        return list;
    }
}
