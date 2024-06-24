package com.fiap.postech.estacionamento.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCadastroValoresAreaDTO {

	private Long AreaAtuacaoId;
	private Double valorPorHora;
	private LocalDateTime dataInicioVigencia;

	public Long getAreaAtuacaoId() {
		return AreaAtuacaoId;
	}

	public void setAreaAtuacaoId(Long areaAtuacaoId) {
		AreaAtuacaoId = areaAtuacaoId;
	}

	public Double getValorPorHora() {
		return valorPorHora;
	}

	public void setValorPorHora(Double valorPorHora) {
		this.valorPorHora = valorPorHora;
	}

	public LocalDateTime getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(LocalDateTime dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

}