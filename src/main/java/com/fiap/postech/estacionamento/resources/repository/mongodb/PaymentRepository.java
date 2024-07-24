package com.fiap.postech.estacionamento.resources.repository.mongodb;

import com.fiap.postech.estacionamento.resources.repository.entities.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {
    Optional<PaymentEntity> findByIdParking(UUID idParking);
}
