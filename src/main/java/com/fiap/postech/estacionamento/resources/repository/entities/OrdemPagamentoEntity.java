package com.fiap.postech.estacionamento.resources.repository.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Document(collection = "ordens_pagamento")
public class OrdemPagamentoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private String id;
	private String idUsuario;
	private String idRegistroEstacionamento;
	private Double valorFinal;
	private int status;

}
