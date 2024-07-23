package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Pagamento {
	private UUID id;
	private Long idUser;
	private Long idPaymentMode;
	private String idParking;
	private Double defaultValueFinal;
	private int status;
}
