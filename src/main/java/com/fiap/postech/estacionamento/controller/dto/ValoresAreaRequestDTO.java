package com.fiap.postech.estacionamento.controller.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

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