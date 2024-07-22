package com.fiap.postech.estacionamento.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VehicleRequestDTO {
    @NotBlank
    private String model;

    @NotBlank
    private String licensePlate;
}