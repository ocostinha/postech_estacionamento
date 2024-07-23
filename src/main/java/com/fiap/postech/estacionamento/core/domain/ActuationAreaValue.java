package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ActuationAreaValue {
	private Long id;
	private Long idActuationArea;
	private Double defaultHourValue;
	private LocalDateTime initialDate;
	private LocalDateTime finalDate;
	private LocalDateTime creationDate;
	private LocalDateTime updatedDate;
}
