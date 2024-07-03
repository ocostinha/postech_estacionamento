package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.FormaPagamentoDTO;
import com.fiap.postech.estacionamento.core.domain.FormaPagamento;
import com.fiap.postech.estacionamento.resources.repository.entities.FormaPagamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface FormaPagamentoMapper {

    FormaPagamento toDomain(FormaPagamentoDTO dto);

    FormaPagamento toDomain(FormaPagamentoEntity entity);

    FormaPagamentoDTO toDto(FormaPagamento domain);


    @Mapping(target = "ativo", constant = "true")
    @Mapping(target = "dataCriacao", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target= "dataUltimaAtualizacao", defaultExpression = "java(LocalDateTime.now())")
    FormaPagamentoEntity toEntity(FormaPagamento domain);


    @Mapping(target = "dataUltimaAtualizacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    FormaPagamentoEntity update(FormaPagamentoDTO formaPagamentoDTO, @MappingTarget FormaPagamentoEntity entity);
}
