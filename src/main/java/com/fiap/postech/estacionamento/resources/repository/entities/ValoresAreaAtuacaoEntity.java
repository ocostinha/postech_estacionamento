package com.fiap.postech.estacionamento.resources.repository.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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