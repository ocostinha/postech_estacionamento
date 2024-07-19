package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Estacionamento {
	private UUID id;
	private Long idUsuario;
	private String placaVeiculo;
	private String idAreaEstacionamento;
	private LocalDateTime dataInicioEstacionamento;
	private LocalDateTime dataFinalEstacionamento;
	private Long idFormaPagamento;
	private Boolean finalizado;
}
