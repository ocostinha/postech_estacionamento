package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.ActuationAreaValueMapper;
import com.fiap.postech.estacionamento.core.domain.ActuationAreaValue;
import com.fiap.postech.estacionamento.resources.repository.ActuationAreaValueRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.ActuationAreaValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActuationAreaValueService {

	@Autowired
	private ActuationAreaValueRepository repository;

	@Autowired
	private ActuationAreaValueMapper mapper;

	public ActuationAreaValue cadastrardefaultValue(ActuationAreaValue ActuationAreaValue) {
		ActuationAreaValueEntity defaultValueExistente = repository
				.findFirstByAreaAtuacaoIdAndDataFimVigenciaIsNull(ActuationAreaValue.getAreaAtuacaoId());

		if (defaultValueExistente != null) {
			defaultValueExistente.setDataFimVigencia(ActuationAreaValue.getDataInicioVigencia().minusDays(1));
			repository.save(defaultValueExistente);
		}

		return mapper.toDomain(
				repository.save(
						mapper.toEntity(ActuationAreaValue)
				)
		);
	}

	public ActuationAreaValue consultardefaultValuePorId(Long id) {
		return mapper.toDomain(repository.findById(id).orElseThrow(() ->
				new NotFoundException("")));
	}

	public List<ActuationAreaValue> consultardefaultValuesPorArea(Long areaAtuacaoId) {
		return repository.findByAreaAtuacaoId(areaAtuacaoId).stream().map(mapper::toDomain).toList();
	}

	public ActuationAreaValue atualizarDataFimVigencia(Long id, LocalDateTime dataFimVigencia) {
		ActuationAreaValueEntity defaultValueRetornado = repository.findById(id).orElseThrow(() ->
				new UnprocessableEntityException("defaultValue não encontrado"));

		if (defaultValueRetornado.getDataInicioVigencia().isAfter(dataFimVigencia)) {
			throw new UnprocessableEntityException("A data de término da vigencia deve ser maior que a data de inicio");
		}

		defaultValueRetornado.setDataFimVigencia(dataFimVigencia);
		defaultValueRetornado.setDataUltimaModificacao(LocalDateTime.now());

		repository.save(defaultValueRetornado);

		return mapper.toDomain(defaultValueRetornado);
	}
}
