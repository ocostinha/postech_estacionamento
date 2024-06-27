package com.fiap.postech.estacionamento.resources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.postech.estacionamento.resources.repository.entities.ValoresAreaAtuacaoEntity;

@Repository
public interface ValoresAreaAtuacaoRepository extends JpaRepository<ValoresAreaAtuacaoEntity, Long> {
	List<ValoresAreaAtuacaoEntity> findByAreaAtuacaoId(Long areaAtuacaoId);

	ValoresAreaAtuacaoEntity findFirstByAreaAtuacaoIdAndDataFimVigenciaIsNull(Long areaAtuacaoId);
}
