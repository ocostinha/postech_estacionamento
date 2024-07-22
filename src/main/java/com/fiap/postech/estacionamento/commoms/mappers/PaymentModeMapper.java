package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.PaymentModeDTO;
import com.fiap.postech.estacionamento.core.domain.PaymentMode;
import com.fiap.postech.estacionamento.resources.repository.entities.PaymentModeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;


@Mapper(componentModel = "spring")
public interface PaymentModeMapper {

    @Mapping(target = "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "creationDate", expression = LOCAL_DATE_TIME_NOW)
    PaymentMode toDomain(PaymentModeDTO dto);

    PaymentMode toDomain(PaymentModeEntity entity);

    PaymentModeDTO toDto(PaymentMode domain);


    @Mapping(target = "active", constant = "true")
    @Mapping(target = "creationDate", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target= "updatedDate", defaultExpression = LOCAL_DATE_TIME_NOW)
    PaymentModeEntity toEntity(PaymentMode domain);


    @Mapping(target = "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "active", ignore = true)
    PaymentModeEntity update(PaymentMode PaymentModeDTO, @MappingTarget PaymentModeEntity entity);
}
