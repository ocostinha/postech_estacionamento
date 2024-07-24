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
	private ActuationAreaService actuationAreaService;

	@Autowired
	private ActuationAreaValueMapper mapper;

	public ActuationAreaValue create(ActuationAreaValue actuationAreaValue) {
		actuationAreaService.validAreaAtuacao(actuationAreaValue.getIdActuationArea());

		if (LocalDateTime.now().plusDays(1).isAfter(actuationAreaValue.getInitialDate())) {
			throw new UnprocessableEntityException(
					"A data de inicio da vigencia deve ser maior que 24 horas contando de agora");
		}

		ActuationAreaValueEntity entity = repository
				.findFirstByIdActuationAreaAndFinalDateIsNull(actuationAreaValue.getIdActuationArea());

		if (entity != null) {
			entity.setFinalDate(actuationAreaValue.getInitialDate().minusDays(1));

			return mapper.toDomain(
					repository.save(entity)
			);
		}

		return mapper.toDomain(
				repository.save(
						mapper.toEntity(actuationAreaValue)
				)
		);
	}

	public ActuationAreaValue getById(Long id) {
		return mapper.toDomain(repository.findById(id).orElseThrow(() ->
				new NotFoundException("Valor da área de atuação não encontrado.")));
	}

	public List<ActuationAreaValue> getByActuationArea(Long idActuationArea) {
		List<ActuationAreaValueEntity> values = repository.findByIdActuationArea(idActuationArea);

		if (values.isEmpty()) {
			throw new NotFoundException("Valores não encontrados para essa área");
		}

		return values.stream().map(mapper::toDomain).toList();
	}

	public Double getActiveValueForActuationArea(Long idActuationArea) {
		return repository.findFirstByIdActuationAreaAndFinalDateIsNull(idActuationArea).getDefaultHourValue();
	}

	public ActuationAreaValue updateFinalDate(Long id, LocalDateTime finalDate) {
		if (LocalDateTime.now().plusDays(1).isAfter(finalDate)) {
			throw new UnprocessableEntityException("A data de término da vigencia deve ser maior que 24 horas contando de agora");
		}

		ActuationAreaValueEntity entity = repository.findById(id).orElseThrow(() ->
				new UnprocessableEntityException("Valor da área de atuação não encontrado."));

		if (entity.getInitialDate().isAfter(finalDate)) {
			throw new UnprocessableEntityException("A data de término da vigencia deve ser maior que a data de inicio.");
		}

		entity.setFinalDate(finalDate);
		entity.setUpdatedDate(LocalDateTime.now());

		return mapper.toDomain(repository.save(entity));
	}
}
