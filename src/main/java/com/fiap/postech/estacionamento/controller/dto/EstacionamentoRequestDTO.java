package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EstacionamentoRequestDTO {
	private Long idUsuario;
	private String placaVeiculo;
	private Long idAreaEstacionamento;
	private LocalDateTime dataInicioEstacionamento;
	private LocalDateTime dataFinalEstacionamento;
	private Long idFormaPagamento;
}
