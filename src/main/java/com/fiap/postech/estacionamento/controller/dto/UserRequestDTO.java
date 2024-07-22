package com.fiap.postech.estacionamento.controller.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDTO {
    @NotBlank
    private String fullName;

    @NotBlank
    private String document;

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 12)
    private String password;

    @NotNull
    @Valid
    private List<VehicleRequestDTO> vehicles;

    @NotNull
    private String idPaymentMode;
}