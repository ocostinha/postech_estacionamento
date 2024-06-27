package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ValoresAreaAtuacao {
	private Long id;
	private Long areaAtuacaoId;
	private Double valorPorHora;
	private LocalDateTime dataInicioVigencia;
	private LocalDateTime dataFimVigencia;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataUltimaModificacao;
}
