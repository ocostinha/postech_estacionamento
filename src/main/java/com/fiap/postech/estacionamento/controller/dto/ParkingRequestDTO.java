package com.fiap.postech.estacionamento.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ParkingRequestDTO {
	@NotNull
	private Long idUser;

	@NotNull
	private String licensePlate;

	@NotNull
	private Long idActuationArea;

	@NotNull
	private LocalDateTime initialDate;

	private LocalDateTime finalDate;

	@NotNull
	private Long idPaymentMode;
}
