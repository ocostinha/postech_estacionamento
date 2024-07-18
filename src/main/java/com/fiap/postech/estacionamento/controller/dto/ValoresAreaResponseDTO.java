package com.fiap.postech.estacionamento.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ValoresAreaResponseDTO {
	private Long id;
	private Long areaAtuacaoId;
	private Double valorPorHora;
	private LocalDateTime dataInicioVigencia;
	private LocalDateTime dataFimVigencia;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataUltimaModificacao;
}
