package br.com.erp.apierp.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
    <R extends Record, T> T obterDados(R dto, Class<T> classe);
}
