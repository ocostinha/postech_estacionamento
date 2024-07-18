package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.UsuarioAtualizacaoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.UsuarioMinimalResponseDTO;
import com.fiap.postech.estacionamento.controller.dto.UsuarioRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.UsuarioResponseDTO;
import com.fiap.postech.estacionamento.core.domain.Usuario;
import com.fiap.postech.estacionamento.resources.repository.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;

@Mapper(componentModel = "spring", uses = VeiculoMapper.class)
public interface UsuarioMapper {
    @Mapping(target = "ativo", constant = "true")
    Usuario toDomain(UsuarioRequestDTO dto);

    @Mapping(target = "ativo", constant = "true")
    Usuario toDomain(UsuarioAtualizacaoRequestDTO dto);

    Usuario toDomain(UsuarioEntity entity);

    UsuarioResponseDTO toResponse(Usuario domain);

    UsuarioMinimalResponseDTO toMinimalResponse(Usuario domain);

    @Mapping(target = "dataCriacao", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "dataUltimaModificacao", defaultExpression = LOCAL_DATE_TIME_NOW)
    UsuarioEntity toEntity(Usuario domain);

    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataUltimaModificacao", expression = LOCAL_DATE_TIME_NOW)
    UsuarioEntity update(Usuario domain, @MappingTarget UsuarioEntity entity);
}
