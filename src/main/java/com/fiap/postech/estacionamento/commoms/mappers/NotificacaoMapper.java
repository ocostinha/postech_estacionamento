package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.core.domain.Notificacao;
import com.fiap.postech.estacionamento.resources.repository.entities.NotificacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;
import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.RANDOM_UUID;

@Mapper(componentModel = "spring")
public interface NotificacaoMapper {
    Notificacao toDomain(NotificacaoEntity entity);

    @Mapping(target="dataEnvio", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target="id", expression = RANDOM_UUID)
    NotificacaoEntity build(UUID idEstacionamento, String email);
}
