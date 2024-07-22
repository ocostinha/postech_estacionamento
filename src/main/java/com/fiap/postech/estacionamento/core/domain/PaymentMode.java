package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PaymentMode {
    private Long id;
    private String description;
    private Boolean defaultValueAccepted;
    private Double defaultValue;
    private Boolean active;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
}
