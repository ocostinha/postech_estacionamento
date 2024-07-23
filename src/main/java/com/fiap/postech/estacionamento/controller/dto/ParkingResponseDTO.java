package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ParkingResponseDTO {
    private UUID id;
    private Long idUser;
    private String licensePlate;
    private Long idActuationArea;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private Long idPaymentMode;
    private Boolean finished;
}
