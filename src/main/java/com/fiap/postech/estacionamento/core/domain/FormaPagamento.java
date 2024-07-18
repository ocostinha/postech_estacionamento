package com.fiap.postech.estacionamento.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class FormaPagamento {
    private Long id;
    private String descricao;
    private boolean aceitaValoresPreEstabelecidos;
    private List<Double> listaValores;
    private boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;
}
