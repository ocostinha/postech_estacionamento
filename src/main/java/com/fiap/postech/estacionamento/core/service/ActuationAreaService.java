package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.ActuationAreaMapper;
import com.fiap.postech.estacionamento.core.domain.ActuationArea;
import com.fiap.postech.estacionamento.resources.repository.AreaAtuacaoRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.AreaAtuacaoEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActuationAreaService {

    @Autowired
    private final AreaAtuacaoRepository areaAtuacaoRepository;

    @Autowired
    private final ActuationAreaMapper mapper;

    public List<ActuationArea> findAll() {
        List<AreaAtuacaoEntity> areas = areaAtuacaoRepository.findByActive(true);

        if (areas.isEmpty()) {
            throw new NotFoundException("Nenhuma área de atuação encontrada.");
        }

        return areas.stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    public ActuationArea findById(Long id) {
        return mapper.toDomain(
                areaAtuacaoRepository.findByIdAndActive(id, true)
                        .orElseThrow(() ->
                                new NotFoundException("Área de atuação não encontrada.")));
    }

    public void validAreaAtuacao(Long id) {
        areaAtuacaoRepository.findByIdAndActive(id, true)
                .orElseThrow(() ->
                        new NotFoundException("Área de atuação não encontrada."));
    }

    public ActuationArea create(ActuationArea actuationArea) {
        return mapper.toDomain(
                areaAtuacaoRepository.save(
                        mapper.toEntity(actuationArea)
                )
        );
    }

    public ActuationArea update(Long id, String descriptionArea) {
        return mapper.toDomain(
                areaAtuacaoRepository.save(
                        mapper.update(descriptionArea, areaAtuacaoRepository.findById(id)
                                .orElseThrow(() -> new UnprocessableEntityException("Área de atuação não encontrada")))
                )
        );
    }

    public ActuationArea disable(Long id) {
        AreaAtuacaoEntity areaAtuacaoEntity = areaAtuacaoRepository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException("Área de atuação não encontrada"));

        areaAtuacaoEntity.setActive(false);

        return mapper.toDomain(
                areaAtuacaoRepository.save(
                        areaAtuacaoEntity
                )
        );
    }
}
