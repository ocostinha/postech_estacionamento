package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoDTO {
	private String id;
	private String idUser;
	private String idParking;
	private Double defaultValueFinal;
	private int status;
}
