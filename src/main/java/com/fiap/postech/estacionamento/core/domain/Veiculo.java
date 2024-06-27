package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Veiculo {
    private Long id;
    private String modelo;
    private String placa;
}