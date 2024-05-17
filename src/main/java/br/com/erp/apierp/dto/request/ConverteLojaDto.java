package br.com.erp.apierp.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ConverteLojaDto(

        @JsonAlias("razao_social") String razaoSocial,
        @JsonAlias("nome_fantasia") String nomeFantasia,
        @JsonAlias("inscricao_estadual") String inscricaoEstadual,
        @JsonAlias("telefone1") String telefone,
        @JsonAlias("email") String email,
        @JsonAlias("cep") String cep,
        @JsonAlias("complemento") String complemento
        ) {
}
