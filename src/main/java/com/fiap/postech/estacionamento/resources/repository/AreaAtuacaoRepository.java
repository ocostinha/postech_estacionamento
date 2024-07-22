package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.AreaAtuacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AreaAtuacaoRepository extends JpaRepository<AreaAtuacaoEntity, Long> {
    Optional<AreaAtuacaoEntity> findByIdAndActive(Long id, Boolean active);
    List<AreaAtuacaoEntity> findByActive(Boolean active);
}
