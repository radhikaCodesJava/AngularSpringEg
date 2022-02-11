package com.demo.assignment.util;

import com.demo.assignment.entity.programEntity;
import com.demo.assignment.model.batchDTO;
import com.demo.assignment.model.programDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-11T11:05:14-0800",
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

        return programDTO;
    }

    @Override
    public programEntity toProgramEntity(batchDTO batchDTO) {
        if ( batchDTO == null ) {
            return null;
        }

        Integer program_id = null;
        String program_name = null;
        String program_description = null;
        String program_status = null;
        Timestamp creation_time = null;
        Timestamp last_mod_time = null;

        programEntity programEntity = new programEntity( program_id, program_name, program_description, program_status, creation_time, last_mod_time );

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
    public List<programEntity> toBatchEntityList(List<programDTO> programDTOs) {
        if ( programDTOs == null ) {
            return null;
        }

        List<programEntity> list = new ArrayList<programEntity>( programDTOs.size() );
        for ( programDTO programDTO : programDTOs ) {
            list.add( programDTOToprogramEntity( programDTO ) );
        }

        return list;
    }

    protected programEntity programDTOToprogramEntity(programDTO programDTO) {
        if ( programDTO == null ) {
            return null;
        }

        Integer program_id = null;
        String program_name = null;
        String program_description = null;
        String program_status = null;
        Timestamp creation_time = null;
        Timestamp last_mod_time = null;

        programEntity programEntity = new programEntity( program_id, program_name, program_description, program_status, creation_time, last_mod_time );

        return programEntity;
    }
}
