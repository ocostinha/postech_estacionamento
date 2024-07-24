package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.PaymentDTO;
import com.fiap.postech.estacionamento.core.domain.Payment;
import com.fiap.postech.estacionamento.resources.repository.entities.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;
import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.RANDOM_UUID;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toDomain(PaymentDTO dto);

    Payment toDomain(PaymentEntity entity);

    @Mapping(target = "value", ignore = true)
    @Mapping(target= "id", expression = RANDOM_UUID)
    @Mapping(target= "status", constant = "0")
    @Mapping(target = "creationDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target= "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    PaymentEntity build(Long idUser, Long idPaymentMode, UUID idParking);

    @Mapping(target = "creationDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target= "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target= "id", defaultExpression = RANDOM_UUID)
    PaymentEntity toEntity(Payment domain);

    @Mapping(target = "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    PaymentEntity update(Payment domain, @MappingTarget PaymentEntity entity);
}
