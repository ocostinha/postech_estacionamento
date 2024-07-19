package com.fiap.postech.estacionamento.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.fiap.postech.estacionamento.resources.repository.mongodb")
public class MongoConfig {
}

