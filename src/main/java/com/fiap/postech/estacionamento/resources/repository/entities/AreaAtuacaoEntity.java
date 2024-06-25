package com.fiap.postech.estacionamento.resources.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="tb_areaAtuacao")
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
    private boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaModificacao;
}
