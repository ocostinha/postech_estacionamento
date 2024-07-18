package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.ValoresAreaAtuacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValoresAreaAtuacaoRepository extends JpaRepository<ValoresAreaAtuacaoEntity, Long> {
	List<ValoresAreaAtuacaoEntity> findByAreaAtuacaoId(Long areaAtuacaoId);

	ValoresAreaAtuacaoEntity findFirstByAreaAtuacaoIdAndDataFimVigenciaIsNull(Long areaAtuacaoId);
}
