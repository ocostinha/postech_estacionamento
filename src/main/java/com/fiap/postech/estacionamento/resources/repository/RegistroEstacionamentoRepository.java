package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.RegistroEstacionamentoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroEstacionamentoRepository extends MongoRepository<RegistroEstacionamentoEntity, String> {
	Optional<RegistroEstacionamentoEntity> findByPlacaVeiculoAndDataFinalEstacionamentoIsNull(String placaVeiculo);
}
