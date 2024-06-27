package com.fiap.postech.estacionamento.controller.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UsuarioResponseDTO {
    private Long id;
    private String nomeCompleto;
    private String documento;
    private String email;
    private List<VeiculoResponseDTO> veiculos;
    private String idFormaPagamento;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaModificacao;
    private boolean ativo;
}