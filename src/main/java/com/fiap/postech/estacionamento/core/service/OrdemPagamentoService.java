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
    private PaymentModeService PaymentModeService;

    @Autowired
    private OrdemPagamentoMapper mapper;

    Pagamento createPayment(Long idUser, Long idPaymentMode, UUID idParking, Integer hoursParked) {
        return mapper.toDomain(
                pagamentoRepository.save(
                        mapper.build(
                                idUser, idPaymentMode, idParking, calcValue(PaymentModeService.defaultValuePix(), hoursParked)
                        )
                )
        );
    }

    public Pagamento findByParkingId(UUID idActuationArea) {
        return mapper.toDomain(pagamentoRepository.findByIdParking(idActuationArea).orElse(null));
    }

    public void atualizarPagamento(Pagamento pagamento) {
        mapper.toDomain(mapper.update(pagamento, mapper.toEntity(pagamento)));
    }

    private Double calcValue(Double value, Integer hour) {
        return value * hour;
    }
}
