package com.fiap.postech.estacionamento.resources.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="tb_estacionamento")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstacionamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private Long idAreaAtuacao;
    private Long idFormaPagamento;
    private LocalDateTime inicioEstacionamento;
    private LocalDateTime finalEstacionamento;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaModificacao;
}
