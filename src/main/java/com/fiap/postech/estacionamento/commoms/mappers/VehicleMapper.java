package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.VehicleRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.VehicleResponseDTO;
import com.fiap.postech.estacionamento.core.domain.Vehicle;
import com.fiap.postech.estacionamento.resources.repository.entities.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    @Mapping(target = "id", ignore = true)
    Vehicle toDomain(VehicleRequestDTO dto);

    Vehicle toDomain(VehicleEntity entity);

    VehicleResponseDTO toResponse(Vehicle domain);

    @Mapping(target = "user", ignore = true)
    VehicleEntity toEntity(Vehicle domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    VehicleEntity update(Vehicle domain, @MappingTarget VehicleEntity entity);
}
