package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.EstacionamentoMapper;
import com.fiap.postech.estacionamento.core.domain.ActuationAreaValue;
import com.fiap.postech.estacionamento.core.domain.Estacionamento;
import com.fiap.postech.estacionamento.core.domain.Pagamento;
import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import com.fiap.postech.estacionamento.resources.repository.mongodb.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EstacionamentoService {
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private OrdemPagamentoService ordemPagamentoService;

    @Autowired
    private ActuationAreaValueService ActuationAreaValueService;

    @Autowired
    private EstacionamentoMapper mapper;

    @Value("${id.pagamento.pix:1}")
    private Long idPagamentoPix;

    public Estacionamento registrar(Estacionamento estacionamento) {
        estacionamentoRepository
                .findByPlacaVeiculoAndFinalizado(estacionamento.getPlacaVeiculo(), false)
                .ifPresent(it -> {
                    throw new UnprocessableEntityException("Veículo já estacionado, verifique os dados.");
                });

        if (estacionamento.getDataFinalEstacionamento() != null &&
                !Objects.equals(estacionamento.getIdPaymentMode(), idPagamentoPix)) {
            throw new UnprocessableEntityException("Período fixo de estacionamento só pode ser pago via PIX");
        }

        EstacionamentoEntity entity = estacionamentoRepository.save(mapper.toEntity(estacionamento));

        if (Objects.equals(estacionamento.getIdPaymentMode(), idPagamentoPix)) {
            Duration timeElapsed = Duration
                    .between(entity.getDataInicioEstacionamento(), entity.getDataFinalEstacionamento());

            ordemPagamentoService.criarPagamentoPix(entity.getIdUsuario(), entity.getId(), timeElapsed.toHoursPart());
        }

        return mapper.toDomain(entity);
    }

    public List<Estacionamento> getExpirados(LocalDateTime dateLimit) {
        return estacionamentoRepository
                .findByDataFinalEstacionamentoBeforeAndFinalizado(dateLimit, false)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    public List<Estacionamento> getFutureExpiration(LocalDateTime initialDate, LocalDateTime finalDate) {
        return estacionamentoRepository
                .findByDataFinalEstacionamentoBetweenAndFinalizado(initialDate, finalDate, false)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
    public void addOneHourLimitPark(UUID id) {
        estacionamentoRepository.findById(id).ifPresent(it -> {
            it.setDataFinalEstacionamento(it.getDataInicioEstacionamento().plusHours(1));

            estacionamentoRepository.save(it);
        });
    }

    public Estacionamento atualizarDataFimEstacionamento(UUID id, String dataFimEstacionamento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dataFimEstacionamento, formatter);

        Estacionamento estacionamento = mapper.toDomain(
                estacionamentoRepository.findById(id).orElseThrow(() ->
                        new NotFoundException("Área de atuação não encontrada")));

        if (estacionamento.getDataFinalEstacionamento() != null) {
            throw new UnprocessableEntityException(
                    "Só é permitido encerrar o registro de estacionamento de um veículo ao qual a " +
                            "finalização não tenha sido realizada.");
        }

        ActuationAreaValue ActuationAreaValue = ActuationAreaValueService.consultardefaultValuesPorArea(
                Long.valueOf(estacionamento.getIdAreaEstacionamento())).get(0);

        if(ActuationAreaValue == null) {
            throw new UnprocessableEntityException("defaultValues para a área de atuação não encontrados.");
        }

        Pagamento ordemPagamento = ordemPagamentoService.findByEstacionamentoId(estacionamento.getId());

        if(!Objects.equals(ordemPagamento.getId(), idPagamentoPix)) {
            Double defaultValueFinal = calculardefaultValuestacionamento(estacionamento.getDataInicioEstacionamento(), estacionamento.getDataFinalEstacionamento(), ActuationAreaValue.getDefaultValuePorHora());

            ordemPagamentoService.criarPagamentoPix(estacionamento.getIdUsuario(), estacionamento.getId(), Integer.valueOf(String.valueOf(defaultValueFinal)));
        }

        LocalDateTime dataFimAjustada = ajustarParaProximaHoraCompleta(dateTime);
        estacionamento.setDataFinalEstacionamento(dataFimAjustada);

        estacionamentoRepository.save(
                mapper.toEntity(estacionamento));

        return estacionamento;
    }

    private LocalDateTime ajustarParaProximaHoraCompleta(LocalDateTime data) {
        return data.plusHours(1).withMinute(0).withSecond(0).withNano(0);
    }

    private Double calculardefaultValuestacionamento(LocalDateTime inicio, LocalDateTime fim, double defaultValuePorHora) {
        return (long) Math.ceil(Duration.between(inicio, fim).toMinutes() / 60.0) * defaultValuePorHora;
    }
}
