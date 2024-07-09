package com.fiap.postech.estacionamento.controller.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistroEstacionamentoResponseDTO {
    private String id;
    private String idUsuario;
    private String placaVeiculo;
    private String idAreaEstacionamento;
    private LocalDateTime dataInicioEstacionamento;
    private LocalDateTime dataFinalEstacionamento;
    private String idFormaPagamento;
    private OrdemPagamentoDTO ordemPagamento;
   
}
