package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.VeiculoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.VeiculoResponseDTO;
import com.fiap.postech.estacionamento.core.domain.Veiculo;
import com.fiap.postech.estacionamento.resources.repository.entities.VeiculoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {
    Veiculo toDomain(VeiculoRequestDTO dto);

    Veiculo toDomain(VeiculoEntity entity);

    VeiculoResponseDTO toResponse(Veiculo domain);

    VeiculoEntity toEntity(Veiculo domain);

    @Mapping(target = "id", ignore = true)
    VeiculoEntity update(Veiculo domain, @MappingTarget VeiculoEntity entity);
}
