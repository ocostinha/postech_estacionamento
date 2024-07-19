package com.fiap.postech.estacionamento.resources.repository.mongodb;

import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EstacionamentoRepository extends MongoRepository<EstacionamentoEntity, UUID> {
    Optional<EstacionamentoEntity> findByPlacaVeiculoAndFinalizado(String placaVeiculo, Boolean finalizado);

    List<EstacionamentoEntity> findByDataFinalEstacionamentoBeforeAndFinalizado(
            LocalDateTime dataLimiteSaida,
            Boolean finalizado
    );

    List<EstacionamentoEntity> findByDataFinalEstacionamentoBetweenAndFinalizado(
            LocalDateTime start,
            LocalDateTime end,
            Boolean finalizado
    );
}
