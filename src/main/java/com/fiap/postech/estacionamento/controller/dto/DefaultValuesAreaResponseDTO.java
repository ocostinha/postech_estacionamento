package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DefaultValuesAreaResponseDTO {
	private Long id;
	private Long areaAtuacaoId;
	private Double defaultValuePorHora;
	private LocalDateTime dataInicioVigencia;
	private LocalDateTime dataFimVigencia;
	private LocalDateTime creationDate;
	private LocalDateTime dataUltimaModificacao;
}
