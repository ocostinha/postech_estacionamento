package com.fiap.postech.estacionamento.resources.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fiap.postech.estacionamento.resources.repository.entities.OrdemPagamentoEntity;
@Repository
public interface OrdemPagamentoRepository extends MongoRepository<OrdemPagamentoEntity, String> {
}
