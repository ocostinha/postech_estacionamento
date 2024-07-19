package com.fiap.postech.estacionamento.resources.repository.mongodb;

import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstacionamentoRepository extends MongoRepository<EstacionamentoEntity, String> {
    Optional<EstacionamentoEntity> findByPlacaVeiculoAndDataFinalEstacionamentoIsNull(String placaVeiculo);

    List<EstacionamentoEntity> findByDataFinalEstacionamentoBeforeAndFinalizado(
            LocalDateTime dataLimiteSaida,
            Boolean finalizado
    );

    List<EstacionamentoEntity> findByDataInicioEstacionamentoBetweenAndFinalizado(
            LocalDateTime start,
            LocalDateTime end,
            Boolean finalizado
    );
}
