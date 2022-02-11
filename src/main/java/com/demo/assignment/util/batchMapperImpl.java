package com.demo.assignment.util;

import com.demo.assignment.entity.batchEntity;
import com.demo.assignment.model.batchDTO;
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
public class batchMapperImpl implements batchMapper {

    @Override
    public batchDTO toBatchDTO(batchEntity savedEntity) {
        if ( savedEntity == null ) {
            return null;
        }

        batchDTO batchDTO = new batchDTO();

        return batchDTO;
    }

    @Override
    public batchEntity toBatchEntity(batchDTO batchDTO) {
        if ( batchDTO == null ) {
            return null;
        }

        batchEntity batchEntity = new batchEntity();

        return batchEntity;
    }

    @Override
    public List<batchDTO> toBatchDTOList(List<batchEntity> batcgEntities) {
        if ( batcgEntities == null ) {
            return null;
        }

        List<batchDTO> list = new ArrayList<batchDTO>( batcgEntities.size() );
        for ( batchEntity batchEntity : batcgEntities ) {
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
}
