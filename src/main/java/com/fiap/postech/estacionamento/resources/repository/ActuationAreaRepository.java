package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.ActuationAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActuationAreaRepository extends JpaRepository<ActuationAreaEntity, Long> {
    Optional<ActuationAreaEntity> findByIdAndActive(Long id, Boolean active);
    List<ActuationAreaEntity> findByActive(Boolean active);
}
