package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.core.domain.Notificacao;
import com.fiap.postech.estacionamento.resources.repository.entities.NotificacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface NotificacaoMapper {
    Notificacao toDomain(NotificacaoEntity entity);

    @Mapping(target= "dataEnvio", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target= "id", defaultExpression = "java(UUID.randomUUID())")
    NotificacaoEntity build(UUID idEstacionamento, String email);
}
