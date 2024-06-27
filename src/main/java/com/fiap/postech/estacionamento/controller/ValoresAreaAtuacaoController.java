package com.fiap.postech.estacionamento.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ConcurrentModificationException;
import java.util.List;

import com.fiap.postech.estacionamento.commoms.exception.BadRequestException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.ValoresAreaAtuacaoMapper;
import com.fiap.postech.estacionamento.controller.dto.ValoresAreaResponseDTO;
import com.fiap.postech.estacionamento.core.domain.ValoresAreaAtuacao;
import com.fiap.postech.estacionamento.core.service.AreaAtuacaoService;
import com.fiap.postech.estacionamento.core.service.ValoresAreaAtuacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fiap.postech.estacionamento.controller.dto.ValoresAreaRequestDTO;

@RestController
@RequestMapping("/valores")
@AllArgsConstructor
public class ValoresAreaAtuacaoController {

    @Autowired
    private final ValoresAreaAtuacaoService service;

    @Autowired
    private final AreaAtuacaoService serviceAreaAtuacao;

    @Autowired
    private final ValoresAreaAtuacaoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ValoresAreaResponseDTO cadastrarValor(@Valid @RequestBody ValoresAreaRequestDTO request) {
        if (LocalDateTime.now().plusDays(1).isAfter(request.getDataInicioVigencia())) {
            throw new BadRequestException("A data de inicio da vigencia deve ser maior que 24 horas contando de agora");
        }

        try {
            serviceAreaAtuacao.validAreaAtuacao(request.getAreaAtuacaoId());
        } catch (Exception ex) {
            throw new UnprocessableEntityException("Não pode ser cadastrado um valor para area  não ativa");
        }

        return mapper.toResponse(
                service.cadastrarValor(
                        mapper.toDomain(request)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ValoresAreaResponseDTO buscarValorPorId(@PathVariable Long id) {
        return mapper.toResponse(service.consultarValorPorId(id));
    }

    @GetMapping("/area")
    @ResponseStatus(HttpStatus.OK)
    public List<ValoresAreaResponseDTO> buscarValoresPorArea(@RequestParam Long areaAtuacaoId) {
        List<ValoresAreaAtuacao> valores = service.consultarValoresPorArea(areaAtuacaoId);
        if (valores.isEmpty()) {
            throw new ConcurrentModificationException("Valores não encontrados para essa área");
        }
        return valores.stream().map(mapper::toResponse).toList();
    }

    @PatchMapping("/{id}")
    public ValoresAreaResponseDTO atualizarDataFimVigencia(@PathVariable Long id,
                                                           @RequestParam String dataFimVigencia) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dataFimVigencia, formatter);

        if (LocalDateTime.now().plusDays(1).isAfter(dateTime)) {
            throw new BadRequestException("A data de término da vigencia deve ser maior que 24 horas contando de agora");
        }

        return mapper.toResponse(service.atualizarDataFimVigencia(id, dateTime));
    }
}
