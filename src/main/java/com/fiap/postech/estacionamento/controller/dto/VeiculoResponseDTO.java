package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoResponseDTO {
    private Long id;
    private String modelo;
    private String placa;
}