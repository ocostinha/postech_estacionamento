package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.ValoresAreaRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.ValoresAreaResponseDTO;
import com.fiap.postech.estacionamento.core.domain.ValoresAreaAtuacao;
import com.fiap.postech.estacionamento.resources.repository.entities.ValoresAreaAtuacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;

@Mapper(componentModel = "spring")
public interface ValoresAreaAtuacaoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataFimVigencia", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataUltimaModificacao", ignore = true)
    ValoresAreaAtuacao toDomain(ValoresAreaRequestDTO dto);
    ValoresAreaAtuacao toDomain(ValoresAreaAtuacaoEntity entity);
    ValoresAreaResponseDTO toResponse(ValoresAreaAtuacao domain);

    @Mapping(target = "dataCriacao", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "dataUltimaModificacao", defaultExpression = LOCAL_DATE_TIME_NOW)
    ValoresAreaAtuacaoEntity toEntity(ValoresAreaAtuacao domain);
}
