package com.fiap.postech.estacionamento.resources.repository.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    private String nomeCompleto;
    private String documento;

    @Column(unique = true)
    private String email;

    private String senha;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<VeiculoEntity> veiculos;

    private String idFormaPagamento;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaModificacao;

    private boolean ativo;
}