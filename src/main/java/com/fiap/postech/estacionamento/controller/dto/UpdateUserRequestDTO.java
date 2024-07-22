package com.fiap.postech.estacionamento.controller.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateUserRequestDTO {
    @NotBlank
    private String fullName;

    @NotBlank
    private String document;

    @NotBlank
    private String email;

    @NotNull
    @Valid
    private List<VehicleRequestDTO> vehicles;

    @NotNull
    private String idPaymentMode;
}