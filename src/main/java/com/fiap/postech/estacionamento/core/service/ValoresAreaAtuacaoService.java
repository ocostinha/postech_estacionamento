package com.fiap.postech.estacionamento.core.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.ValoresAreaAtuacaoMapper;
import com.fiap.postech.estacionamento.core.domain.ValoresAreaAtuacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.postech.estacionamento.resources.repository.entities.ValoresAreaAtuacaoEntity;
import com.fiap.postech.estacionamento.resources.repository.ValoresAreaAtuacaoRepository;

@Service
public class ValoresAreaAtuacaoService {

	@Autowired
	private ValoresAreaAtuacaoRepository repository;

	@Autowired
	private ValoresAreaAtuacaoMapper mapper;

	public ValoresAreaAtuacao cadastrarValor(ValoresAreaAtuacao valoresAreaAtuacao) {
		ValoresAreaAtuacaoEntity valorExistente = repository
				.findFirstByAreaAtuacaoIdAndDataFimVigenciaIsNull(valoresAreaAtuacao.getAreaAtuacaoId());

		if (valorExistente != null) {
			valorExistente.setDataFimVigencia(valoresAreaAtuacao.getDataInicioVigencia().minusDays(1));
			repository.save(valorExistente);
		}

		return mapper.toDomain(
				repository.save(
						mapper.toEntity(valoresAreaAtuacao)
				)
		);
	}

	public ValoresAreaAtuacao consultarValorPorId(Long id) {
		return mapper.toDomain(repository.findById(id).orElseThrow(() ->
				new NotFoundException("")));
	}

	public List<ValoresAreaAtuacao> consultarValoresPorArea(Long areaAtuacaoId) {
		return repository.findByAreaAtuacaoId(areaAtuacaoId).stream().map(mapper::toDomain).toList();
	}

	public ValoresAreaAtuacao atualizarDataFimVigencia(Long id, LocalDateTime dataFimVigencia) {
		ValoresAreaAtuacaoEntity valorRetornado = repository.findById(id).orElseThrow(() ->
				new UnprocessableEntityException("Valor não encontrado"));

		if (valorRetornado.getDataInicioVigencia().isAfter(dataFimVigencia)) {
			throw new UnprocessableEntityException("A data de término da vigencia deve ser maior que a data de inicio");
		}

		valorRetornado.setDataFimVigencia(dataFimVigencia);
		valorRetornado.setDataUltimaModificacao(LocalDateTime.now());

		repository.save(valorRetornado);

		return mapper.toDomain(valorRetornado);
	}
}
