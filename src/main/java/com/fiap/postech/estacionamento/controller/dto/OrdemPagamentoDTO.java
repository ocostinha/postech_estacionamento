package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemPagamentoDTO {
	private String id;
	private String idUsuario;
	private String idEstacionamento;
	private Double valorFinal;
	private int status;
}
