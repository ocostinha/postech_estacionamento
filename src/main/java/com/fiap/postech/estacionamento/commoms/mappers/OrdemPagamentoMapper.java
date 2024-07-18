package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.OrdemPagamentoDTO;
import com.fiap.postech.estacionamento.core.domain.OrdemPagamento;
import com.fiap.postech.estacionamento.resources.repository.entities.OrdemPagamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrdemPagamentoMapper {
    OrdemPagamento toDomain(OrdemPagamentoDTO dto);

    OrdemPagamento toDomain(OrdemPagamentoEntity entity);

    OrdemPagamentoDTO toDto(OrdemPagamento domain);


    @Mapping(target= "id", defaultExpression = "java(UUID.randomUUID())")
    @Mapping(target= "status", constant = "0")
    OrdemPagamentoEntity build(Long idUsuario, UUID idRegistroEstacionamento, Double valorFinal);

    @Mapping(target = "ativo", constant = "true")
    @Mapping(target = "dataCriacao", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target= "dataUltimaAtualizacao", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target= "id", defaultExpression = "java(UUID.randomUUID())")
    OrdemPagamentoEntity toEntity(OrdemPagamento domain);

    @Mapping(target = "dataUltimaAtualizacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    OrdemPagamentoEntity update(OrdemPagamento domain, @MappingTarget OrdemPagamentoEntity entity);
}
