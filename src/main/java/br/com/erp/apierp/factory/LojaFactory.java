package br.com.erp.apierp.factory;

import br.com.erp.apierp.dto.request.RequestLoja;
import br.com.erp.apierp.dto.request.RequestLojaAutomatizado;
import br.com.erp.apierp.model.Loja;

public interface LojaFactory {

    Loja criaLojaApiCnpj(RequestLojaAutomatizado dados);

    Loja criaLoja(RequestLoja dados);

    void atualizaLoja(Loja loja, RequestLoja dto);
}
