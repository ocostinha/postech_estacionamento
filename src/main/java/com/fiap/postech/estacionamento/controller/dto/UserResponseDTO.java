package com.fiap.postech.estacionamento.controller.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String fullName;
    private String document;
    private String email;
    private List<VehicleResponseDTO> vehicles;
    private String idPaymentMode;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
    private boolean active;
}