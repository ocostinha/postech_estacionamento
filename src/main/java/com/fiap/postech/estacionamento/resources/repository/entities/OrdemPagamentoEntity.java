package com.fiap.postech.estacionamento.resources.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="tb_ordem_pagamento")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdemPagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long estacionamentoId;
    private Long usuarioId;
    private double valorFinal;
    private int status; // 0 = EM ABERTO, 1 = PAGO, etc.
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;
}
