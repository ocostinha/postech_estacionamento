package com.fiap.postech.estacionamento.controller.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ActuationAreaValueRequestDTO {
	@NotNull
	private Long idActuationArea;

	@NotNull
	@Positive
	private Double defaultHourValue;

	@Future
	private LocalDateTime initialDate;
}