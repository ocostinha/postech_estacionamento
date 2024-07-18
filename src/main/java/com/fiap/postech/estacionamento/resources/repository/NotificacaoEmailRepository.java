package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.NotificacaoEmailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificacaoEmailRepository extends MongoRepository<NotificacaoEmailEntity, String> {
}
