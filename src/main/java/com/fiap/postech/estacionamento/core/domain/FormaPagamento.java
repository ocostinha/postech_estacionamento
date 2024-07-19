package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FormaPagamento {
    private Long id;
    private String descricao;
    private Boolean aceitaValorPreEstabelecido;
    private Double valor;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;
}
