package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.core.domain.Notify;
import com.fiap.postech.estacionamento.resources.repository.entities.NotifyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;
import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.RANDOM_UUID;

@Mapper(componentModel = "spring")
public interface NotifyMapper {
    Notify toDomain(NotifyEntity entity);

    @Mapping(target="sentDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target="id", expression = RANDOM_UUID)
    NotifyEntity build(UUID idParking, String email);
}
