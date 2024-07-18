package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class RegistroEstacionamentoResponseDTO {
    private UUID id;
    private Long idUsuario;
    private String placaVeiculo;
    private Long idAreaEstacionamento;
    private LocalDateTime dataInicioEstacionamento;
    private LocalDateTime dataFinalEstacionamento;
    private Long idFormaPagamento;
}
