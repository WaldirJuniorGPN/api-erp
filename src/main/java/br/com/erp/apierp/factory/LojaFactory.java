package br.com.erp.apierp.factory;

import br.com.erp.apierp.dto.request.RequestLoja;
import br.com.erp.apierp.dto.request.RequestLojaAutomatizado;
import br.com.erp.apierp.model.Loja;

public interface LojaFactory<T, D extends Record> extends EntityFactory<Loja, RequestLoja> {

    T criarApiCnpj(RequestLojaAutomatizado dto);
}
