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
	private double defaultValueHour;
	private LocalDateTime dataInicioVigencia;
	private LocalDateTime dataFimVigencia;
	private LocalDateTime creationDate;
	private LocalDateTime dataUltimaModificacao;
	private Long areaAtuacaoId;
}