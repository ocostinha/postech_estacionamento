package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.EstacionamentoEmAbertoException;
import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.EstacionamentoMapper;
import com.fiap.postech.estacionamento.commoms.mappers.FormaPagamentoMapper;
import com.fiap.postech.estacionamento.core.domain.AreaAtuacao;
import com.fiap.postech.estacionamento.core.domain.Estacionamento;
import com.fiap.postech.estacionamento.resources.repository.EstacionamentoRepository;
import com.fiap.postech.estacionamento.resources.repository.FormaPagamentoRepository;
import com.fiap.postech.estacionamento.resources.repository.OrdemPagamentoRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.AreaAtuacaoEntity;
import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import com.fiap.postech.estacionamento.resources.repository.entities.FormaPagamentoEntity;
import com.fiap.postech.estacionamento.resources.repository.entities.OrdemPagamentoEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EstacionamentoService {

    private static final Long PIX_ID = 1L;

    private final FormaPagamentoRepository formaPagamentoRepository;

    private final OrdemPagamentoRepository ordemPagamentoRepository;

    @Autowired
    private final EstacionamentoRepository repository;

    @Autowired
    private final EstacionamentoMapper mapper;

    // 4.
    public Estacionamento registrarEstacionamento(Estacionamento estacionamento) {
        verificarEstacionamentoEmAberto(estacionamento.placa());
        validarFormaPagamento(estacionamento);

        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(estacionamento)
                )
        );
    }

    // 2.
    public void verificarEstacionamentoEmAberto(String placa) {
        repository.findByPlacaAndFinalEstacionamentoIsNull(placa)
                .ifPresent(estacionamento -> {
                    throw new EstacionamentoEmAbertoException("Só é permitido estacionar um veículo se o registro anterior tiver sido finalizado.");
                });
    }

    // 3.
    private void validarFormaPagamento(Estacionamento estacionamento) {
        if (estacionamento.finalEstacionamento() != null) {
            FormaPagamentoEntity formaPagamentoPIX = formaPagamentoRepository.findByDescricaoAndAtivo("PIX", true)
                    .orElseThrow(() -> new UnprocessableEntityException("Forma de pagamento PIX não encontrada ou não está ativa."));

            if (!estacionamento.idFormaPagamento().equals(formaPagamentoPIX.getId())) {
                throw new UnprocessableEntityException("O período fixo de estacionamento só pode ser pago via PIX.");
            }

            criarOrdemPagamento(estacionamento);
        }
    }

    private void criarOrdemPagamento(Estacionamento estacionamento) {
        double valorFinal = calcularValorEstacionamento(estacionamento.inicioEstacionamento(), estacionamento.finalEstacionamento());

        OrdemPagamentoEntity ordemPagamento = new OrdemPagamentoEntity();
        ordemPagamento.setEstacionamentoId(estacionamento.id());

        // Id do usuário de quem ta fazendo o cadastro do estacionamento do cliente?
        ordemPagamento.setUsuarioId(1L);
        ordemPagamento.setValorFinal(valorFinal);
        ordemPagamento.setStatus(0);
        ordemPagamento.setDataCriacao(LocalDateTime.now());
        ordemPagamento.setDataUltimaAtualizacao(LocalDateTime.now());

        ordemPagamentoRepository.save(ordemPagamento);
    }

    private double calcularValorEstacionamento(LocalDateTime inicio, LocalDateTime fim) {
        Duration duracao = Duration.between(inicio, fim);

        // Qual valor colocar aqui?
        return duracao.toHours() * 10;
    }

    public Estacionamento consultarEstacionamentoPorId(Long id) {
        return mapper.toDomain(
                repository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException("Estacionamento não encontrado")));
    }

    public List<Estacionamento> consultarEstacionamentosPorPlaca(String placa) {

        List<EstacionamentoEntity> estacionamentos = repository.findByPlaca(placa);

        if (estacionamentos.isEmpty()) {
            throw new NotFoundException("Nenhum estacionamento encontrado para esta placa");
        }

        return estacionamentos.stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
