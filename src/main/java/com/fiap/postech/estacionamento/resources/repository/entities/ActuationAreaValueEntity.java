package com.fiap.postech.estacionamento.resources.repository.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class ActuationAreaValueEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double defaultHourValue;
	private LocalDateTime initialDate;
	private LocalDateTime finalDate;
	private LocalDateTime creationDate;
	private LocalDateTime updatedDate;
	private Long idActuationArea;
}