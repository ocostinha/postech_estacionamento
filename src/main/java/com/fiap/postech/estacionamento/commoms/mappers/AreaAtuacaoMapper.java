package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.AreaAtuacaoDTO;
import com.fiap.postech.estacionamento.core.domain.AreaAtuacao;
import com.fiap.postech.estacionamento.resources.repository.entities.AreaAtuacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;

@Mapper(componentModel = "spring")
public interface AreaAtuacaoMapper {
    @Mapping(target = "ativo", constant = "true")
    AreaAtuacao toDomain(AreaAtuacaoDTO dto);

    @Mapping(source = "nomeArea", target = "nome")
    AreaAtuacao toDomain(AreaAtuacaoEntity entity);

    AreaAtuacaoDTO toDto(AreaAtuacao domain);

    @Mapping(target = "dataCriacao", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "dataUltimaModificacao", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(source = "nome", target = "nomeArea")
    AreaAtuacaoEntity toEntity(AreaAtuacao domain);

    @Mapping(target = "dataUltimaModificacao", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cidade", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(source = "nomeArea", target = "nomeArea")
    AreaAtuacaoEntity update(String nomeArea, @MappingTarget AreaAtuacaoEntity entity);
}
