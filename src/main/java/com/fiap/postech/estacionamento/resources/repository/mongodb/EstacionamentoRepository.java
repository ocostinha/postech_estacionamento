package com.fiap.postech.estacionamento.resources.repository.mongodb;

import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface EstacionamentoRepository extends MongoRepository<EstacionamentoEntity, String> {
    List<EstacionamentoEntity> findByDataLimiteSaidaBefore(LocalDateTime dateTime);
}
