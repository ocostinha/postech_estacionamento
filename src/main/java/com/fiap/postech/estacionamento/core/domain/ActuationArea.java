package com.fiap.postech.estacionamento.core.domain;

import java.time.LocalDateTime;

public record ActuationArea(
        Long id,
        String description,
        String city,
        String state,
        boolean active,
        LocalDateTime creationDate,
        LocalDateTime updatedDate
) {

}

