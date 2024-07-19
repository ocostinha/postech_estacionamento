package com.fiap.postech.estacionamento.resources.repository.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Data
@Document(collection = "ordens_pagamento")
public class OrdemPagamentoEntity {
	@MongoId
	private UUID id;
	private Long idUsuario;
	private UUID idEstacionamento;
	private Double valorFinal;
	private int status;
}
