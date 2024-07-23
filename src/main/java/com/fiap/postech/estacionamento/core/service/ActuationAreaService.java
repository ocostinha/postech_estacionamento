package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.ActuationAreaMapper;
import com.fiap.postech.estacionamento.core.domain.ActuationArea;
import com.fiap.postech.estacionamento.resources.repository.ActuationAreaRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.ActuationAreaEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActuationAreaService {

    @Autowired
    private final ActuationAreaRepository actuationAreaRepository;

    @Autowired
    private final ActuationAreaMapper mapper;

    public List<ActuationArea> findAll() {
        List<ActuationAreaEntity> areas = actuationAreaRepository.findByActive(true);

        if (areas.isEmpty()) {
            throw new NotFoundException("Nenhuma área de atuação encontrada.");
        }

        return areas.stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    public ActuationArea findById(Long id) {
        return mapper.toDomain(
                actuationAreaRepository.findByIdAndActive(id, true)
                        .orElseThrow(() ->
                                new NotFoundException("Área de atuação não encontrada.")));
    }

    public void validAreaAtuacao(Long id) {
        actuationAreaRepository.findByIdAndActive(id, true)
                .orElseThrow(() ->
                        new NotFoundException("Área de atuação não encontrada."));
    }

    public ActuationArea create(ActuationArea actuationArea) {
        return mapper.toDomain(
                actuationAreaRepository.save(
                        mapper.toEntity(actuationArea)
                )
        );
    }

    public ActuationArea update(Long id, String descriptionArea) {
        return mapper.toDomain(
                actuationAreaRepository.save(
                        mapper.update(descriptionArea, actuationAreaRepository.findById(id)
                                .orElseThrow(() -> new UnprocessableEntityException("Área de atuação não encontrada")))
                )
        );
    }

    public ActuationArea disable(Long id) {
        ActuationAreaEntity actuationAreaEntity = actuationAreaRepository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException("Área de atuação não encontrada"));

        actuationAreaEntity.setActive(false);

        return mapper.toDomain(
                actuationAreaRepository.save(
                        actuationAreaEntity
                )
        );
    }
}
