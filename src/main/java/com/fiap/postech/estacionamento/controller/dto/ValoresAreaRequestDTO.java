package com.fiap.postech.estacionamento.controller.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ValoresAreaRequestDTO {
	@NotNull
	private Long areaAtuacaoId;

	@NotNull
	@Positive
	private Double valorPorHora;

	@Future
	private LocalDateTime dataInicioVigencia;
}