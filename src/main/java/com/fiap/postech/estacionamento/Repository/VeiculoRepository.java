package com.fiap.postech.estacionamento.Repository;


import com.fiap.postech.estacionamento.Entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
