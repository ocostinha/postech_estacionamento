package com.fiap.postech.estacionamento.controller.dto;

import java.time.LocalDateTime;

public record ActuationAreaResponseDTO(
        Long id,
        String description,
        String city,
        String state,
        boolean active,
        LocalDateTime creationDate,
        LocalDateTime updatedDate
) {

}

