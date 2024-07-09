package com.fiap.postech.estacionamento.resources.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fiap.postech.estacionamento.resources.repository.entities.RegistroEstacionamentoEntity;

@Repository
public interface RegistroEstacionamentoRepository extends MongoRepository<RegistroEstacionamentoEntity, String> {
	Optional<RegistroEstacionamentoEntity> findByPlacaVeiculoAndDataFinalEstacionamentoIsNull(String placaVeiculo);
}
