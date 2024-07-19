package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.EstacionamentoMapper;
import com.fiap.postech.estacionamento.core.domain.Estacionamento;
import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import com.fiap.postech.estacionamento.resources.repository.mongodb.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private OrdemPagamentoService ordemPagamentoService;

    @Autowired
    private EstacionamentoMapper mapper;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    private com.fiap.postech.estacionamento.Service.EmailService emailService;

    public Estacionamento registrar(Estacionamento estacionamento) {
        Optional<EstacionamentoEntity> estacionamentoExistente = estacionamentoRepository
                .findByPlacaVeiculoAndDataFinalEstacionamentoIsNull(estacionamento.getPlacaVeiculo());

        if (estacionamentoExistente.isPresent()) {
            throw new UnprocessableEntityException(
                    "Veículo já estacionado, verifique os dados.");
        }

        if (estacionamento.getDataFinalEstacionamento() != null && !estacionamento.getIdFormaPagamento().equals("PIX")) {
            throw new UnprocessableEntityException("Período fixo de estacionamento só pode ser pago via PIX");
        }

        EstacionamentoEntity entity = estacionamentoRepository.save(mapper.toEntity(estacionamento));

        if ("PIX".equals(estacionamento.getIdFormaPagamento())) {
            ordemPagamentoService.criarPagamento(entity.getIdUsuario(), entity.getId());
        }

        return mapper.toDomain(entity);
    }

    public void notifyExpirados() {
        getExpirados(LocalDateTime.now().withSecond(59).minusMinutes(1)).forEach(estacionamento -> {
            String email = usuarioService.getUserById(estacionamento.getIdUsuario()).getEmail();

            emailService.sendEmail(
                    email,
                    "Tempo de Estacionamento Esgotado", "Seu tempo de estacionamento acabou."
            );

            notificacaoService.create(estacionamento.getId(), email);
        });
    }

    private List<Estacionamento> getExpirados(LocalDateTime dateLimit) {
        return estacionamentoRepository
                .findByDataFinalEstacionamentoBeforeAndFinalizado(dateLimit, false)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    public void alertExpiracao() {
        getFutureExpiration(
                LocalDateTime.now().minusMinutes(50).withSecond(0),
                LocalDateTime.now().minusMinutes(50).withSecond(59)
        ).forEach(estacionamento -> {
            String email = usuarioService.getUserById(estacionamento.getIdUsuario()).getEmail();

            emailService.sendEmail(email,
                    "Aviso de Expiração de Estacionamento",
                    "Sua hora de estacionamento está prestes a expirar, " +
                            "ela termirá em 10 minutos e será renovado automáticamente por mais uma hora!"
            );

            notificacaoService.create(estacionamento.getId(), email);
        });
    }

    private List<Estacionamento> getFutureExpiration(LocalDateTime initialDate, LocalDateTime finalDate) {
        return estacionamentoRepository
                .findByDataInicioEstacionamentoBetweenAndFinalizado(initialDate, finalDate, false)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
