package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {
    private Long id;
    private String model;
    private String licensePlate;
}