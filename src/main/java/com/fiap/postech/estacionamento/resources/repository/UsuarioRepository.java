package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmailAndAtivo(String email, Boolean ativo);
    Optional<UsuarioEntity> findByEmailAndSenhaAndAtivo(String email, String senha, Boolean ativo);
}