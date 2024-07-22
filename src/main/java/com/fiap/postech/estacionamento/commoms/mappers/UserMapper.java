package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.MinimalUserResponseDTO;
import com.fiap.postech.estacionamento.controller.dto.UpdateUserRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.UserRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.UserResponseDTO;
import com.fiap.postech.estacionamento.core.domain.User;
import com.fiap.postech.estacionamento.resources.repository.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;

@Mapper(componentModel = "spring", uses = VehicleMapper.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    User toDomain(UserRequestDTO dto);

    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    User toDomain(UpdateUserRequestDTO dto);

    User toDomain(UsuarioEntity entity);

    UserResponseDTO toResponse(User domain);

    @Mapping(target = "idFavoritePaymentMode", source = "idPaymentMode")
    MinimalUserResponseDTO toMinimalResponse(User domain);

    @Mapping(target = "creationDate", defaultExpression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "updatedDate", defaultExpression = LOCAL_DATE_TIME_NOW)
    UsuarioEntity toEntity(User domain);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    UsuarioEntity update(User domain, @MappingTarget UsuarioEntity entity);
}
