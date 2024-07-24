package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Payment {
	private UUID id;
	private Long idUser;
	private Long idPaymentMode;
	private UUID idParking;
	private Double value;
	private int status;
}
