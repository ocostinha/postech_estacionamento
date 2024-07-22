package com.fiap.postech.estacionamento.resources.repository;

import com.fiap.postech.estacionamento.resources.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndActive(String email, Boolean active);
    Optional<UserEntity> findByIdAndActive(Long id, Boolean active);
    Optional<UserEntity> findByEmailAndPasswordAndActive(String email, String password, Boolean active);
}