package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.FormaPagamentoMapper;
import com.fiap.postech.estacionamento.core.domain.FormaPagamento;
import com.fiap.postech.estacionamento.resources.repository.FormaPagamentoRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.FormaPagamentoEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoMapper mapper;

    @Value("${id.pagamento.pix:1}")
    private Long idPagamentoPix;

    public List<FormaPagamento> findAll() {
        List<FormaPagamentoEntity> formasPagamentos = formaPagamentoRepository.findByAtivo(true);
        return formasPagamentos.stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    public FormaPagamento save(FormaPagamento formaPagamento) {
        if (!formaPagamento.getAceitaValorPreEstabelecido()) {
            formaPagamento.setValor(0.0);
        }

        return mapper.toDomain(
                formaPagamentoRepository.save(
                        mapper.toEntity(formaPagamento)
                )
        );
    }

    public FormaPagamento findById(Long id) {
        return mapper.toDomain(
                formaPagamentoRepository.findByIdAndAtivo(id, true)
                        .orElseThrow(() ->
                                new NotFoundException("Forma de Pagamento não encontrada")));
    }

    public FormaPagamento desativar(Long id) {
        try {
            FormaPagamentoEntity formaPagamentoEntity = formaPagamentoRepository.getReferenceById(id);
            formaPagamentoEntity.setAtivo(false);

            return mapper.toDomain(
                    formaPagamentoRepository.save(
                            formaPagamentoEntity
                    )
            );
        } catch (EntityNotFoundException e) {
            throw new UnprocessableEntityException("Forma de pagamento não encontrada");
        }
    }

    public FormaPagamento update(Long id, FormaPagamento formaPagamento) {
        try {
            if (!formaPagamento.getAceitaValorPreEstabelecido()) {
                formaPagamento.setValor(0.0);
            }

            return mapper.toDomain(
                    formaPagamentoRepository.save(
                            mapper.update(formaPagamento, formaPagamentoRepository.getReferenceById(id))
                    )
            );
        } catch (EntityNotFoundException e) {
            throw new UnprocessableEntityException("Forma de pagamento não encontrada");
        }
    }

    public Double valorPix() {
        return formaPagamentoRepository
                .findByIdAndAtivo(idPagamentoPix, true)
                .orElseThrow(() -> new UnprocessableEntityException("Forma de pagamento PIX não encontrado"))
                .getValor();
    }
}
