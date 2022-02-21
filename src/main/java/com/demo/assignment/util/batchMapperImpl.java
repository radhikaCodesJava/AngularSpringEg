package com.demo.assignment.util;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.entity.programEntity;
import com.demo.assignment.model.batchDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-17T13:15:48-0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class batchMapperImpl implements batchMapper {

    @Override
    public batchDTO toBatchDTO(batchEntity savedEntity) {
        if ( savedEntity == null ) {
            return null;
        }

        batchDTO batchDTO = new batchDTO();

        batchDTO.setBatch_program_id( savedEntityProgramEntity_batchProgramId( savedEntity ) );
        batchDTO.setBatch_id( savedEntity.getBatch_id() );
        batchDTO.setBatch_name( savedEntity.getBatch_name() );
        batchDTO.setBatch_description( savedEntity.getBatch_description() );
        batchDTO.setBatch_status( savedEntity.getBatch_status() );
        if ( savedEntity.getBatch_num_classes() != null ) {
            batchDTO.setBatch_num_classes( savedEntity.getBatch_num_classes() );
        }
        batchDTO.setCreation_time( savedEntity.getCreation_time() );
        batchDTO.setLast_modified_time( savedEntity.getLast_modified_time() );

        return batchDTO;
    }

    @Override
    public batchEntity toBatchEntity(batchDTO batchDTO) {
        if ( batchDTO == null ) {
            return null;
        }

        batchEntity batchEntity = new batchEntity();

        batchEntity.setProgramEntity_batch( batchDTOToprogramEntity( batchDTO ) );
        batchEntity.setBatch_id( batchDTO.getBatch_id() );
        batchEntity.setBatch_name( batchDTO.getBatch_name() );
        batchEntity.setBatch_description( batchDTO.getBatch_description() );
        batchEntity.setBatch_status( batchDTO.getBatch_status() );
        batchEntity.setBatch_num_classes( batchDTO.getBatch_num_classes() );
        batchEntity.setCreation_time( batchDTO.getCreation_time() );
        batchEntity.setLast_modified_time( batchDTO.getLast_modified_time() );

        return batchEntity;
    }

    @Override
    public List<batchDTO> toBatchDTOList(List<batchEntity> batchEntities) {
        if ( batchEntities == null ) {
            return null;
        }

        List<batchDTO> list = new ArrayList<batchDTO>( batchEntities.size() );
        for ( batchEntity batchEntity : batchEntities ) {
            list.add( toBatchDTO( batchEntity ) );
        }

        return list;
    }

    @Override
    public List<batchEntity> toBatchEntityList(List<batchDTO> batchDTOs) {
        if ( batchDTOs == null ) {
            return null;
        }

        List<batchEntity> list = new ArrayList<batchEntity>( batchDTOs.size() );
        for ( batchDTO batchDTO : batchDTOs ) {
            list.add( toBatchEntity( batchDTO ) );
        }

        return list;
    }

    private Integer savedEntityProgramEntity_batchProgramId(batchEntity batchEntity) {
        if ( batchEntity == null ) {
            return null;
        }
        programEntity programEntity_batch = batchEntity.getProgramEntity_batch();
        if ( programEntity_batch == null ) {
            return null;
        }
        Integer programId = programEntity_batch.getProgramId();
        if ( programId == null ) {
            return null;
        }
        return programId;
    }

    protected programEntity batchDTOToprogramEntity(batchDTO batchDTO) {
        if ( batchDTO == null ) {
            return null;
        }

        programEntity programEntity = new programEntity();

        programEntity.setProgramId( batchDTO.getBatch_program_id() );

        return programEntity;
    }
}
