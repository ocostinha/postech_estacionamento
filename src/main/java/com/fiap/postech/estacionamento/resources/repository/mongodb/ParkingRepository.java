package com.fiap.postech.estacionamento.resources.repository.mongodb;

import com.fiap.postech.estacionamento.resources.repository.entities.ParkingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkingRepository extends MongoRepository<ParkingEntity, UUID> {
    Optional<ParkingEntity> findByLicensePlateAndFinished(String licensePlate, Boolean finished);
    Optional<ParkingEntity> findByLicensePlateAndFinalDateIsNotNull(String licensePlate);

    List<ParkingEntity> findByFinalDateBeforeAndFinished(
            LocalDateTime finalDate,
            Boolean finished
    );

    List<ParkingEntity> findByFinalDateBetweenAndFinished(
            LocalDateTime start,
            LocalDateTime end,
            Boolean finished
    );
}
