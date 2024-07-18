package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.RegistroEstacionamentoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.RegistroEstacionamentoResponseDTO;
import com.fiap.postech.estacionamento.core.domain.RegistroEstacionamento;
import com.fiap.postech.estacionamento.resources.repository.entities.RegistroEstacionamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RegistroEstacionamentoMapper {
    @Mapping(target= "id", defaultExpression = "java(UUID.randomUUID())")
    RegistroEstacionamento toDomain(RegistroEstacionamentoRequestDTO dto);

    RegistroEstacionamento toDomain(RegistroEstacionamentoEntity entity);

    RegistroEstacionamentoResponseDTO toDto(RegistroEstacionamento domain);

    @Mapping(target = "ativo", constant = "true")
    @Mapping(target = "dataCriacao", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target= "dataUltimaAtualizacao", defaultExpression = "java(LocalDateTime.now())")
    RegistroEstacionamentoEntity toEntity(RegistroEstacionamento domain);

    @Mapping(target = "dataUltimaAtualizacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    RegistroEstacionamentoEntity update(RegistroEstacionamento domain, @MappingTarget RegistroEstacionamentoEntity entity);
}
