package com.fiap.postech.estacionamento.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VeiculoRequestDTO {
    @NotBlank
    private String modelo;

    @NotBlank
    private String placa;
}