package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.PaymentModeMapper;
import com.fiap.postech.estacionamento.core.domain.PaymentMode;
import com.fiap.postech.estacionamento.resources.repository.PaymentModeRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.PaymentModeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentModeService {

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    private PaymentModeMapper mapper;

    public List<PaymentMode> findAll() {
        List<PaymentModeEntity> modes = paymentModeRepository.findByActive(true);
        return modes.stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    public PaymentMode create(PaymentMode PaymentMode) {
        return mapper.toDomain(
                paymentModeRepository.save(
                        mapper.toEntity(PaymentMode)
                )
        );
    }

    public PaymentMode findById(Long id) {
        return mapper.toDomain(
                paymentModeRepository.findByIdAndActive(id, true)
                        .orElseThrow(() ->
                                new NotFoundException("Forma de Pagamento não encontrada")));
    }

    public PaymentMode disable(Long id) {
        PaymentModeEntity entity = paymentModeRepository.findById(id).orElseThrow(() ->
                new UnprocessableEntityException("Forma de pagamento não encontrada"));

        entity.setActive(false);

        return mapper.toDomain(
                paymentModeRepository.save(
                        entity
                )
        );
    }

    public PaymentMode update(Long id, PaymentMode paymentMode) {
        return mapper.toDomain(
                paymentModeRepository.save(
                        mapper.update(
                                paymentMode,
                                paymentModeRepository.findById(id).orElseThrow(
                                        () -> new UnprocessableEntityException("Forma de pagamento não encontrada")
                                )
                        )
                )
        );
    }
}
