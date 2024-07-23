package com.fiap.postech.estacionamento.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ActuationAreaRequestDTO(
        @NotBlank(message = "Nome da área é obrigatório")
        String description,
        @NotBlank(message = "Cidade é obrigatória")
        String city,
        @NotBlank(message = "Estado é obrigatório")
        String state
) {

}

