package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponseDTO {
    private Long id;
    private String model;
    private String licensePlate;
}