package com.fiap.postech.estacionamento.controller.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioAtualizacaoRequestDTO {
    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String documento;

    @NotBlank
    private String email;

    @NotNull
    @Valid
    private List<VeiculoRequestDTO> veiculos;

    @NotNull
    private String idFormaPagamento;
}