package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoDTO {
	private String id;
	private String idUsuario;
	private String idEstacionamento;
	private Double defaultValueFinal;
	private int status;
}
