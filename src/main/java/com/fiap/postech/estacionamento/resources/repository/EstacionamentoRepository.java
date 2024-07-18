package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstacionamentoRepository  extends JpaRepository<EstacionamentoEntity, Long> {
    Optional<EstacionamentoEntity> findByPlacaAndFinalEstacionamentoIsNull(String placa);
    List<EstacionamentoEntity> findByPlaca(String placa);
}
