package br.com.erp.apierp.factory;

public interface EntityFactory<T, D extends Record> {

    T criar(D dto);

    void atualizar(T classe, D dto);
}
