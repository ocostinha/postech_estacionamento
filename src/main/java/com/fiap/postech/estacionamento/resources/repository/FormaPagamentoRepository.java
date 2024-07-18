package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.FormaPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoEntity, Long> {
    Optional<FormaPagamentoEntity> findByIdAndAtivo(Long id, Boolean ativo);
    List<FormaPagamentoEntity> findByAtivo(Boolean ativo);
}
