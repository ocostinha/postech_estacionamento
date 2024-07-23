package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.ParkingRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.ParkingResponseDTO;
import com.fiap.postech.estacionamento.core.domain.Parking;
import com.fiap.postech.estacionamento.resources.repository.entities.ParkingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;
import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.RANDOM_UUID;

@Mapper(componentModel = "spring")
public interface ParkingMapper {
    @Mapping(target= "id", expression = RANDOM_UUID)
    @Mapping(target = "finished", constant = "false")
    Parking toDomain(ParkingRequestDTO dto);

    Parking toDomain(ParkingEntity entity);

    ParkingResponseDTO toDto(Parking domain);

    @Mapping(target = "creationDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target= "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(
            target= "finalDate",
            defaultExpression = "java(domain.getInitialDate().plusHours(1))"
    )
    ParkingEntity toEntity(Parking domain);

    @Mapping(target = "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "finished", ignore = true)
    ParkingEntity update(Parking domain, @MappingTarget ParkingEntity entity);
}
