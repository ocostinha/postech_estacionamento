package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.DefaultValuesAreaRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.DefaultValuesAreaResponseDTO;
import com.fiap.postech.estacionamento.core.domain.ActuationAreaValue;
import com.fiap.postech.estacionamento.resources.repository.entities.ActuationAreaValueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;

@Mapper(componentModel = "spring")
public interface ActuationAreaValueMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataFimVigencia", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "dataUltimaModificacao", ignore = true)
    ActuationAreaValue toDomain(DefaultValuesAreaRequestDTO dto);
    ActuationAreaValue toDomain(ActuationAreaValueEntity entity);
    DefaultValuesAreaResponseDTO toResponse(ActuationAreaValue domain);

    @Mapping(target = "creationDate", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "dataUltimaModificacao", defaultExpression = LOCAL_DATE_TIME_NOW)
    ActuationAreaValueEntity toEntity(ActuationAreaValue domain);
}
