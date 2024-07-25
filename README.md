# Tech Challenge Estacionamento - Pós Tech

## Técnologias

O sistema de estacionamento foi desenvolvido utilizando 
* Java 21
* Spring Boot 3
* Spring JPA
* Spring Data MongoDB
* Spring Cache
* Spring Quartz
* Spring Mail
* Spring Validation
* Postgres
* MongoDB
* Mapstruct
* Lombok

## Features

As instruções para as features foram seguidas conforme o documento compartilhado.
Incluímos uma feature extra para adicionar áreas de atuação, portanto será necessário cadastrar a área e um valor para essa área.
Portanto, as features são:

* CRUD área de atuação
* CRUD valores da área de atuação
* CRUD usuários
* CRUD formas de pagamento
* Registro de entrada e saída de veículos
* Envio de notifição e recibos

## Fluxos de cadastro

O fluxo de cadastro no sistema será:
1. Área de atuação
2. Valor por hora da área de atuação
3. Forma de pagamento
4. Usuário

Após os cadastros poderá então ser realizada a entreda e saída de veículos.

## Iniciando o sistema localmente

O sitema utiliza como banco de dados Postgres e MongoDB, portanto deve-se utilizar um container docker com imagens Postgres e MongoDB
Para iniciar o sistema utilizamos o comando `docker-compose up` na pasta que contém o arquivo docker-compose.yml (\docker) em uma máquina com o Docker instalado.

## Endpoints do sistema

O swagger pode ser acessado localmente em: http://localhost:8080/docs.html

