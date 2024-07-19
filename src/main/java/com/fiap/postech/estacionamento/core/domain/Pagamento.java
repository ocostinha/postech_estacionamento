package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Pagamento {
	private UUID id;
	private String idUsuario;
	private String idEstacionamento;
	private Double valorFinal;
	private int status;
}
