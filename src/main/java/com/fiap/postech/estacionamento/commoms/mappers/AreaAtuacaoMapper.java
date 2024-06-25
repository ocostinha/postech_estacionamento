package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.AreaAtuacaoDTO;
import com.fiap.postech.estacionamento.core.domain.AreaAtuacao;
import com.fiap.postech.estacionamento.resources.repository.entities.AreaAtuacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AreaAtuacaoMapper {
    AreaAtuacao toDomain(AreaAtuacaoDTO dto);

    @Mapping(source = "nomeArea", target = "nome")
    AreaAtuacao toDomain(AreaAtuacaoEntity entity);

    AreaAtuacaoDTO toDto(AreaAtuacao domain);

    @Mapping(target = "ativo", defaultValue = "true")
    @Mapping(target = "dataCriacao", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target = "dataUltimaModificacao", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(source = "nome", target = "nomeArea")
    AreaAtuacaoEntity toEntity(AreaAtuacao domain);

    @Mapping(target = "dataUltimaModificacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cidade", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(source = "nomeArea", target = "nomeArea")
    AreaAtuacaoEntity update(String nomeArea, @MappingTarget AreaAtuacaoEntity entity);
}
