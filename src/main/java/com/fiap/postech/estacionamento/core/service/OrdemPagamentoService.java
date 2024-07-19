package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.mappers.OrdemPagamentoMapper;
import com.fiap.postech.estacionamento.core.domain.OrdemPagamento;
import com.fiap.postech.estacionamento.resources.repository.OrdemPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrdemPagamentoService {
    @Autowired
    private OrdemPagamentoRepository ordemPagamentoRepository;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private OrdemPagamentoMapper mapper;

    OrdemPagamento criarPagamento(Long idUsuario, UUID idEstacionamento) {
        return mapper.toDomain(
                ordemPagamentoRepository.save(
                        mapper.build(
                                idUsuario, idEstacionamento, formaPagamentoService.valorEstacionamentoPix()
                        )
                )
        );
    }
}
