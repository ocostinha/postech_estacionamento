package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.exception.BadRequestException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.ActuationAreaValueMapper;
import com.fiap.postech.estacionamento.controller.dto.DefaultValuesAreaRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.DefaultValuesAreaResponseDTO;
import com.fiap.postech.estacionamento.core.domain.ActuationAreaValue;
import com.fiap.postech.estacionamento.core.service.ActuationAreaValueService;
import com.fiap.postech.estacionamento.core.service.AreaAtuacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ConcurrentModificationException;
import java.util.List;

@RestController
@RequestMapping("/defaultValues")
@AllArgsConstructor
public class ActuationAreaValueController {

    @Autowired
    private final ActuationAreaValueService service;

    @Autowired
    private final AreaAtuacaoService serviceAreaAtuacao;

    @Autowired
    private final ActuationAreaValueMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DefaultValuesAreaResponseDTO cadastrardefaultValue(@Valid @RequestBody DefaultValuesAreaRequestDTO request) {
        if (LocalDateTime.now().plusDays(1).isAfter(request.getDataInicioVigencia())) {
            throw new BadRequestException("A data de inicio da vigencia deve ser maior que 24 horas contando de agora");
        }

        try {
            serviceAreaAtuacao.validAreaAtuacao(request.getAreaAtuacaoId());
        } catch (Exception ex) {
            throw new UnprocessableEntityException("Não pode ser cadastrado um defaultValue para area  não ativa");
        }

        return mapper.toResponse(
                service.cadastrardefaultValue(
                        mapper.toDomain(request)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DefaultValuesAreaResponseDTO buscardefaultValuePorId(@PathVariable Long id) {
        return mapper.toResponse(service.consultardefaultValuePorId(id));
    }

    @GetMapping("/area")
    @ResponseStatus(HttpStatus.OK)
    public List<DefaultValuesAreaResponseDTO> buscardefaultValuesPorArea(@RequestParam Long areaAtuacaoId) {
        List<ActuationAreaValue> defaultValues = service.consultardefaultValuesPorArea(areaAtuacaoId);
        if (defaultValues.isEmpty()) {
            throw new ConcurrentModificationException("defaultValues não encontrados para essa área");
        }
        return defaultValues.stream().map(mapper::toResponse).toList();
    }

    @PatchMapping("/{id}")
    public DefaultValuesAreaResponseDTO atualizarDataFimVigencia(@PathVariable Long id,
                                                                 @RequestParam String dataFimVigencia) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dataFimVigencia, formatter);

        if (LocalDateTime.now().plusDays(1).isAfter(dateTime)) {
            throw new BadRequestException("A data de término da vigencia deve ser maior que 24 horas contando de agora");
        }

        return mapper.toResponse(service.atualizarDataFimVigencia(id, dateTime));
    }
}
