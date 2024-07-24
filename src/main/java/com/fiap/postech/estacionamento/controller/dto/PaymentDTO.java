package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentDTO {
	private UUID id;
	private Long idUser;
	private Long idPaymentMode;
	private UUID idParking;
	private Double value;
	private int status;
}
