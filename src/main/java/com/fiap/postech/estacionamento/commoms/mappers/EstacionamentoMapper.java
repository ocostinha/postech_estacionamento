package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.EstacionamentoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.EstacionamentoResponseDTO;
import com.fiap.postech.estacionamento.core.domain.Estacionamento;
import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EstacionamentoMapper {
    @Mapping(target= "id", defaultExpression = "java(UUID.randomUUID())")
    @Mapping(target = "finalizado", constant = "false")
    Estacionamento toDomain(EstacionamentoRequestDTO dto);

    Estacionamento toDomain(EstacionamentoEntity entity);

    EstacionamentoResponseDTO toDto(Estacionamento domain);

    @Mapping(target = "ativo", constant = "true")
    @Mapping(target = "dataCriacao", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target= "dataUltimaAtualizacao", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(
            target= "dataFinalEstacionamento",
            defaultExpression = "java(domain.getDataInicioEstacionamento().plusHours(1))"
    )
    EstacionamentoEntity toEntity(Estacionamento domain);

    @Mapping(target = "dataUltimaAtualizacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    EstacionamentoEntity update(Estacionamento domain, @MappingTarget EstacionamentoEntity entity);
}
