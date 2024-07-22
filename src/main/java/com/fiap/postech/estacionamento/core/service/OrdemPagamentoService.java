package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.mappers.OrdemPagamentoMapper;
import com.fiap.postech.estacionamento.core.domain.Pagamento;
import com.fiap.postech.estacionamento.resources.repository.mongodb.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrdemPagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private OrdemPagamentoMapper mapper;

    Pagamento criarPagamentoPix(Long idUsuario, UUID idEstacionamento, Integer hoursParked) {
        return mapper.toDomain(
                pagamentoRepository.save(
                        mapper.build(
                                idUsuario, idEstacionamento, calcValue(formaPagamentoService.valorPix(), hoursParked)
                        )
                )
        );
    }

    public Pagamento findByEstacionamentoId(UUID areaAtuacaoId) {
        return mapper.toDomain(pagamentoRepository.findByEstacionamentoId(areaAtuacaoId));
    }

    public void atualizarPagamento(Pagamento pagamento) {
        mapper.toDomain(mapper.update(pagamento, mapper.toEntity(pagamento)));
    }

    private Double calcValue(Double value, Integer hour) {
        return value * hour;
    }
}
