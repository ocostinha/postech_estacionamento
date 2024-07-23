package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.ActuationAreaRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.ActuationAreaResponseDTO;
import com.fiap.postech.estacionamento.core.domain.ActuationArea;
import com.fiap.postech.estacionamento.resources.repository.entities.AreaAtuacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;

@Mapper(componentModel = "spring")
public interface ActuationAreaMapper {
    @Mapping(target = "active", constant = "true")
    ActuationArea toDomain(ActuationAreaRequestDTO dto);

    @Mapping(source = "descriptionArea", target = "description")
    ActuationArea toDomain(AreaAtuacaoEntity entity);

    ActuationAreaResponseDTO toResponse(ActuationArea domain);

    @Mapping(target = "creationDate", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "updatedDate", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(source = "description", target = "descriptionArea")
    AreaAtuacaoEntity toEntity(ActuationArea domain);

    @Mapping(target = "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(source = "descriptionArea", target = "descriptionArea")
    AreaAtuacaoEntity update(String descriptionArea, @MappingTarget AreaAtuacaoEntity entity);
}
