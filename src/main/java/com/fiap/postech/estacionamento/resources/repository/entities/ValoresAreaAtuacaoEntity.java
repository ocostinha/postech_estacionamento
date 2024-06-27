package com.fiap.postech.estacionamento.resources.repository.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "valores_area_atuacao")
public class ValoresAreaAtuacaoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double valorPorHora;
	private LocalDateTime dataInicioVigencia;
	private LocalDateTime dataFimVigencia;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataUltimaModificacao;
	private Long areaAtuacaoId;
}