package com.fiap.postech.estacionamento.controller.dto;


import jakarta.validation.constraints.NotBlank;

public record PaymentModeDTO(
        Long id,
        @NotBlank(message = "Forma de pagamento é obrigatória")
        String description,
        Boolean defaultValueAccepted,
        Double defaultValue,
        Boolean active
) {
}
