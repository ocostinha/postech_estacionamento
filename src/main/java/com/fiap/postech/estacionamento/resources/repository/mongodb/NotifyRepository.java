package com.fiap.postech.estacionamento.resources.repository.mongodb;

import com.fiap.postech.estacionamento.resources.repository.entities.NotifyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotifyRepository extends MongoRepository<NotifyEntity, String> {
}