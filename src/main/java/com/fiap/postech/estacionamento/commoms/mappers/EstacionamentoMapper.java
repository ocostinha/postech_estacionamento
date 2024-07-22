package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.EstacionamentoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.EstacionamentoResponseDTO;
import com.fiap.postech.estacionamento.core.domain.Estacionamento;
import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;
import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.RANDOM_UUID;

@Mapper(componentModel = "spring")
public interface EstacionamentoMapper {
    @Mapping(target= "id", expression = RANDOM_UUID)
    @Mapping(target = "finalizado", constant = "false")
    Estacionamento toDomain(EstacionamentoRequestDTO dto);

    Estacionamento toDomain(EstacionamentoEntity entity);

    EstacionamentoResponseDTO toDto(Estacionamento domain);

    @Mapping(target = "creationDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target= "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(
            target= "dataFinalEstacionamento",
            defaultExpression = "java(domain.getDataInicioEstacionamento().plusHours(1))"
    )
    EstacionamentoEntity toEntity(Estacionamento domain);

    @Mapping(target = "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "finalizado", ignore = true)
    EstacionamentoEntity update(Estacionamento domain, @MappingTarget EstacionamentoEntity entity);
}
