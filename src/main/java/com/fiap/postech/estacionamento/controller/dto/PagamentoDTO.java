package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PagamentoDTO {
	private UUID id;
	private Long idUser;
	private Long idPaymentMode;
	private UUID idParking;
	private Double defaultValueFinal;
	private int status;
}
