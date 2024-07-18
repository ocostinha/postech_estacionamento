package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.RegistroEstacionamentoMapper;
import com.fiap.postech.estacionamento.core.domain.RegistroEstacionamento;
import com.fiap.postech.estacionamento.resources.repository.RegistroEstacionamentoRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.RegistroEstacionamentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistroEstacionamentoService {
    @Autowired
    private RegistroEstacionamentoRepository estacionamentoRepository;

    @Autowired
    private OrdemPagamentoService ordemPagamentoService;

    @Autowired
    private RegistroEstacionamentoMapper mapper;

    public RegistroEstacionamento registrarEstacionamento(RegistroEstacionamento registro) {
        Optional<RegistroEstacionamentoEntity> estacionamentoExistente = estacionamentoRepository
                .findByPlacaVeiculoAndDataFinalEstacionamentoIsNull(registro.getPlacaVeiculo());

        if (estacionamentoExistente.isPresent()) {
            throw new UnprocessableEntityException(
                    "Só é possível estacionar um veículo por vez. Finalize o registro anterior.");
        }

        if (registro.getDataFinalEstacionamento() != null && !registro.getIdFormaPagamento().equals("PIX")) {
            throw new UnprocessableEntityException("Período fixo de estacionamento só pode ser pago via PIX");
        }

        RegistroEstacionamentoEntity entity = estacionamentoRepository.save(mapper.toEntity(registro));

        if ("PIX".equals(registro.getIdFormaPagamento())) {
            ordemPagamentoService.criarPagamento(entity.getIdUsuario(), entity.getId());
        }

        return mapper.toDomain(entity);
    }
}
