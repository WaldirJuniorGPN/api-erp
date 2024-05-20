package br.com.erp.apierp.dto.response;

import br.com.erp.apierp.model.Loja;

public record ResponseLojaBuscaSimples(Long id, String nome, String cnpj) {
    public ResponseLojaBuscaSimples(Loja loja) {
        this(loja.getId(), loja.getNomeFantasia(), loja.getCnpj());
    }
}
