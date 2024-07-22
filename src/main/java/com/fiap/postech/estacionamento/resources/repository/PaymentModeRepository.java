package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.PaymentModeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentModeRepository extends JpaRepository<PaymentModeEntity, Long> {
    Optional<PaymentModeEntity> findByIdAndActive(Long id, Boolean active);
    List<PaymentModeEntity> findByActive(Boolean active);
    Optional<PaymentModeEntity> findByDescriptionAndActive(String description, Boolean active);
}
