package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.*;
import com.fiap.postech.estacionamento.core.domain.Estacionamento;
import com.fiap.postech.estacionamento.core.domain.FormaPagamento;
import com.fiap.postech.estacionamento.resources.repository.entities.AreaAtuacaoEntity;
import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;

@Mapper(componentModel = "spring")
public interface EstacionamentoMapper {
    Estacionamento toDomain(EstacionamentoRequestDTO dto);
    Estacionamento toDomain(EstacionamentoEntity entity);
    EstacionamentoResponseDTO toDto(Estacionamento domain);
    EstacionamentoResponseDTO toResponse(Estacionamento domain);
    EstacionamentoEntity toEntity(Estacionamento domain);

    @Mapping(target = "dataCriacao", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "dataUltimaModificacao", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "placa", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idAreaAtuacao", ignore = true)
    @Mapping(target = "idFormaPagamento", ignore = true)
    @Mapping(target = "inicioEstacionamento", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "finalEstacionamento", ignore = true)
    AreaAtuacaoEntity update(String placa, @MappingTarget EstacionamentoEntity entity);
}
