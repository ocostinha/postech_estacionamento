package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.OrdemPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemPagamentoRepository extends JpaRepository<OrdemPagamentoEntity, Long> {
}
