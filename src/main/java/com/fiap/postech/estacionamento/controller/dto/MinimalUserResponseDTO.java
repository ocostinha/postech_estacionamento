package com.fiap.postech.estacionamento.controller.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MinimalUserResponseDTO {
    private String fullName;
    private List<VehicleResponseDTO> vehicles;
    private String idFavoritePaymentMode;
}