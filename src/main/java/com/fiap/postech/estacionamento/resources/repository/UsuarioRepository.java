package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmailAndActive(String email, Boolean active);
    Optional<UsuarioEntity> findByIdAndActive(Long id, Boolean active);
    Optional<UsuarioEntity> findByEmailAndPasswordAndActive(String email, String password, Boolean active);
}