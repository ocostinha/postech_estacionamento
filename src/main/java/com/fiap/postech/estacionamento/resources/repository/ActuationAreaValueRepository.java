package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.ActuationAreaValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActuationAreaValueRepository extends JpaRepository<ActuationAreaValueEntity, Long> {
	List<ActuationAreaValueEntity> findByIdActuationArea(Long idActuationArea);

	ActuationAreaValueEntity findFirstByIdActuationAreaAndFinalDateIsNull(Long idActuationArea);
}
