package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.PaymentModeMapper;
import com.fiap.postech.estacionamento.core.domain.PaymentMode;
import com.fiap.postech.estacionamento.resources.repository.PaymentModeRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.PaymentModeEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentModeService {

    @Autowired
    private PaymentModeRepository PaymentModeRepository;

    @Autowired
    private PaymentModeMapper mapper;

    @Value("${id.pagamento.pix:1}")
    private Long idPixPaymentMode;

    public List<PaymentMode> findAll() {
        List<PaymentModeEntity> modes = PaymentModeRepository.findByActive(true);
        return modes.stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    public PaymentMode create(PaymentMode PaymentMode) {
        if (!PaymentMode.getDefaultValueAccepted()) {
            PaymentMode.setDefaultValue(0.0);
        }

        return mapper.toDomain(
                PaymentModeRepository.save(
                        mapper.toEntity(PaymentMode)
                )
        );
    }

    public PaymentMode findById(Long id) {
        return mapper.toDomain(
                PaymentModeRepository.findByIdAndActive(id, true)
                        .orElseThrow(() ->
                                new NotFoundException("Forma de Pagamento não encontrada")));
    }

    public PaymentMode disable(Long id) {
        try {
            PaymentModeEntity PaymentModeEntity = PaymentModeRepository.getReferenceById(id);
            PaymentModeEntity.setActive(false);

            return mapper.toDomain(
                    PaymentModeRepository.save(
                            PaymentModeEntity
                    )
            );
        } catch (EntityNotFoundException e) {
            throw new UnprocessableEntityException("Forma de pagamento não encontrada");
        }
    }

    public PaymentMode update(Long id, PaymentMode paymentMode) {
        try {
            if (!paymentMode.getDefaultValueAccepted()) {
                paymentMode.setDefaultValue(0.0);
            }

            return mapper.toDomain(
                    PaymentModeRepository.save(
                            mapper.update(paymentMode, PaymentModeRepository.getReferenceById(id))
                    )
            );
        } catch (EntityNotFoundException e) {
            throw new UnprocessableEntityException("Forma de pagamento não encontrada");
        }
    }

    public Double defaultValuePix() {
        return PaymentModeRepository
                .findByIdAndActive(idPixPaymentMode, true)
                .orElseThrow(() -> new UnprocessableEntityException("Forma de pagamento PIX não encontrado"))
                .getDefaultValue();
    }
}
