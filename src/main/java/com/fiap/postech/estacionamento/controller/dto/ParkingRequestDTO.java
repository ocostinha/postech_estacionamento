package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ParkingRequestDTO {
	private Long idUser;
	private String licensePlate;
	private Long idAreaParking;
	private LocalDateTime initialDate;
	private LocalDateTime finalDate;
	private Long idPaymentMode;
}
