package com.fiap.postech.estacionamento.resources.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AreaAtuacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeArea;
    private String cidade;
    private String estado;
    private boolean active;
    private LocalDateTime creationDate;
    private LocalDateTime dataUltimaModificacao;
}
