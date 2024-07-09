package com.fiap.postech.estacionamento.core.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroEstacionamento {

	private String id;
	private String idUsuario;
	private String placaVeiculo;
	private String idAreaEstacionamento;
	private LocalDateTime dataInicioEstacionamento;
	private LocalDateTime dataFinalEstacionamento;
	private String idFormaPagamento;

}
