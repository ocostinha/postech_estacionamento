package com.fiap.postech.estacionamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.postech.estacionamento.entity.ValoresAreaAtuacao;

@Repository
public interface ValoresAreaAtuacaoRepository extends JpaRepository<ValoresAreaAtuacao, Long> {
	List<ValoresAreaAtuacao> findByAreaAtuacaoId(Long areaAtuacaoId);

	ValoresAreaAtuacao findFirstByAreaAtuacaoIdAndDataFimVigenciaIsNull(Long areaAtuacaoId);
}
