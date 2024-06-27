package com.fiap.postech.estacionamento.commoms.mappers.utils;

import java.time.LocalDateTime;

public class MappingUtils {
    public static final String LOCAL_DATE_TIME_NOW =
            "java(com.fiap.postech.estacionamento.commoms.mappers.utils.MappingUtils.localDateTimeNow())";

    public static LocalDateTime localDateTimeNow() {
        return LocalDateTime.now();
    }
}
