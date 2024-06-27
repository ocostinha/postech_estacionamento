package com.fiap.postech.estacionamento.controller.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioRequestDTO {
    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String documento;

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 12)
    private String senha;

    @NotNull
    @Valid
    private List<VeiculoRequestDTO> veiculos;

    @NotNull
    private String idFormaPagamento;
}