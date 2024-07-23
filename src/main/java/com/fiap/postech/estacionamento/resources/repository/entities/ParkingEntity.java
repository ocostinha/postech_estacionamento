package com.fiap.postech.estacionamento.resources.repository.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document
public class ParkingEntity {
    @MongoId
    private UUID id;
    private Long idUser;
    private String licensePlate;
    private Long idActuationArea;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private Long idPaymentMode;
    private Boolean finished;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
}
