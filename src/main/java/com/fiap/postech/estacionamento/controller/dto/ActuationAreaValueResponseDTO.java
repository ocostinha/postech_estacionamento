package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ActuationAreaValueResponseDTO {
	private Long id;
	private Long idActuationArea;
	private Double defaultHourValue;
	private LocalDateTime initialDate;
	private LocalDateTime finalDate;
	private LocalDateTime creationDate;
	private LocalDateTime updatedDate;
}
