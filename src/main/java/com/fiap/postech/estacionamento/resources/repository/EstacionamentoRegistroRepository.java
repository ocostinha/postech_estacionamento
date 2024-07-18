package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoRegistroEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EstacionamentoRegistroRepository extends MongoRepository<EstacionamentoRegistroEntity, String> {
    List<EstacionamentoRegistroEntity> findByHoraFimIsNullAndHoraInicioLessThanEqual(LocalDateTime time);
}
