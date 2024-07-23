package com.fiap.postech.estacionamento.commoms.mappers;

import com.fiap.postech.estacionamento.controller.dto.PagamentoDTO;
import com.fiap.postech.estacionamento.core.domain.Pagamento;
import com.fiap.postech.estacionamento.resources.repository.entities.PagamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.LOCAL_DATE_TIME_NOW;
import static com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.RANDOM_UUID;

@Mapper(componentModel = "spring")
public interface OrdemPagamentoMapper {
    Pagamento toDomain(PagamentoDTO dto);

    Pagamento toDomain(PagamentoEntity entity);

    PagamentoDTO toDto(Pagamento domain);

    @Mapping(target= "id", expression = RANDOM_UUID)
    @Mapping(target= "status", constant = "0")
    @Mapping(target = "creationDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target= "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    PagamentoEntity build(Long idUser, Long idPaymentMode, UUID idParking, Double defaultValueFinal);

    @Mapping(target = "creationDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target= "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target= "id", defaultExpression = RANDOM_UUID)
    PagamentoEntity toEntity(Pagamento domain);

    @Mapping(target = "updatedDate", expression = LOCAL_DATE_TIME_NOW)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    PagamentoEntity update(Pagamento domain, @MappingTarget PagamentoEntity entity);
}
