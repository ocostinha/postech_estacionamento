package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.ActuationAreaValueRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.ActuationAreaValueResponseDTO;
import com.fiap.postech.estacionamento.core.domain.ActuationAreaValue;
import com.fiap.postech.estacionamento.resources.repository.entities.ActuationAreaValueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;

@Mapper(componentModel = "spring")
public interface ActuationAreaValueMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "finalDate", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    ActuationAreaValue toDomain(ActuationAreaValueRequestDTO dto);
    ActuationAreaValue toDomain(ActuationAreaValueEntity entity);
    ActuationAreaValueResponseDTO toResponse(ActuationAreaValue domain);

    @Mapping(target = "creationDate", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "updatedDate", defaultExpression = LOCAL_DATE_TIME_NOW)
    ActuationAreaValueEntity toEntity(ActuationAreaValue domain);
}
