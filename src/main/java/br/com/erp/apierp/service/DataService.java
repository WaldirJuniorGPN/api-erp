package br.com.erp.apierp.service;

import br.com.erp.apierp.dto.request.ConverteLojaDto;

public interface DataService {
    <T> T obterDados(String json, Class<T> classe);
    <R extends Record, T> T obterDados(R dto, Class<T> classe);
    String buscaEnderecoApi (String cep);
    ConverteLojaDto converteJson(String json);
    String obterDadosCnpj(String cnpj);
}
