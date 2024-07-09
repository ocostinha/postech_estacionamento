package com.fiap.postech.estacionamento.controller.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioMinimalResponseDTO  {
    private String nomeCompleto;
    private List<VeiculoResponseDTO> veiculos;
    private String idFormaPagamentoFavorita;
}