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
    @Mapping(target = "id", ignore = true)
    Veiculo toDomain(VeiculoRequestDTO dto);

    Veiculo toDomain(VeiculoEntity entity);

    VeiculoResponseDTO toResponse(Veiculo domain);

    @Mapping(target = "usuario", ignore = true)
    VeiculoEntity toEntity(Veiculo domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    VeiculoEntity update(Veiculo domain, @MappingTarget VeiculoEntity entity);
}
