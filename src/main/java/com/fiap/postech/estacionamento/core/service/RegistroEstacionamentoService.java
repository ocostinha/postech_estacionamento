package com.fiap.postech.estacionamento.core.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fiap.postech.estacionamento.controller.dto.OrdemPagamentoDTO;
import com.fiap.postech.estacionamento.controller.dto.RegistroEstacionamentoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.RegistroEstacionamentoResponseDTO;
import com.fiap.postech.estacionamento.resources.repository.OrdemPagamentoRepository;
import com.fiap.postech.estacionamento.resources.repository.RegistroEstacionamentoRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.OrdemPagamentoEntity;
import com.fiap.postech.estacionamento.resources.repository.entities.RegistroEstacionamentoEntity;

@Service
public class RegistroEstacionamentoService {
    @Autowired
    private RegistroEstacionamentoRepository estacionamentoRepository;

    @Autowired
    private OrdemPagamentoRepository ordemPagamentoRepository;

    public RegistroEstacionamentoResponseDTO registrarEstacionamento(RegistroEstacionamentoRequestDTO request) {
        Optional<RegistroEstacionamentoEntity> estacionamentoExistente = estacionamentoRepository
                .findByPlacaVeiculoAndDataFinalEstacionamentoIsNull(request.getPlacaVeiculo());
        if (estacionamentoExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Só é possível estacionar um veículo por vez. Finalize o registro anterior.");
        }

        if (request.getDataFinalEstacionamento() != null && !request.getIdFormaPagamento().equals("PIX")) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Período fixo de estacionamento só pode ser pago via PIX");
        }

        RegistroEstacionamentoEntity estacionamento = new RegistroEstacionamentoEntity();
        estacionamento.setIdUsuario(request.getIdUsuario());
        estacionamento.setPlacaVeiculo(request.getPlacaVeiculo());
        estacionamento.setIdAreaEstacionamento(request.getIdAreaEstacionamento());
        estacionamento.setDataInicioEstacionamento(request.getDataInicioEstacionamento());
        estacionamento.setDataFinalEstacionamento(request.getDataFinalEstacionamento());
        estacionamento.setIdFormaPagamento(request.getIdFormaPagamento());

        estacionamentoRepository.save(estacionamento);

        OrdemPagamentoDTO ordemPagamentoDTO = null;
        if ("PIX".equals(request.getIdFormaPagamento())) {
            OrdemPagamentoEntity ordemPagamento = new OrdemPagamentoEntity();
            ordemPagamento.setIdUsuario(request.getIdUsuario());
            ordemPagamento.setIdRegistroEstacionamento(estacionamento.getId());
            ordemPagamento.setValorFinal(calcularValorFinal(estacionamento));
            ordemPagamento.setStatus(0); // EM ABERTO
            ordemPagamentoRepository.save(ordemPagamento);

            ordemPagamentoDTO = new OrdemPagamentoDTO();
            ordemPagamentoDTO.setId(ordemPagamento.getId());
            ordemPagamentoDTO.setIdUsuario(ordemPagamento.getIdUsuario());
            ordemPagamentoDTO.setIdRegistroEstacionamento(ordemPagamento.getIdRegistroEstacionamento());
            ordemPagamentoDTO.setValorFinal(ordemPagamento.getValorFinal());
            ordemPagamentoDTO.setStatus(ordemPagamento.getStatus());
        }

        RegistroEstacionamentoResponseDTO response = new RegistroEstacionamentoResponseDTO();
        response.setId(estacionamento.getId());
        response.setIdUsuario(estacionamento.getIdUsuario());
        response.setPlacaVeiculo(estacionamento.getPlacaVeiculo());
        response.setIdAreaEstacionamento(estacionamento.getIdAreaEstacionamento());
        response.setDataInicioEstacionamento(estacionamento.getDataInicioEstacionamento());
        response.setDataFinalEstacionamento(estacionamento.getDataFinalEstacionamento());
        response.setIdFormaPagamento(estacionamento.getIdFormaPagamento());
        response.setOrdemPagamento(ordemPagamentoDTO);

        return response;
    }

    private Double calcularValorFinal(RegistroEstacionamentoEntity estacionamento) {
        // Implementar calculo do valor final do estacionamento
    	
        return 0.0; 
    }
}
