package br.com.erp.apierp.dto.response;

import br.com.erp.apierp.model.Loja;

public record ResponseLoja(Long id, String razaoSocial, String nomeFantasia, String inscricaoEstadual, String telefone,
                           String email, ResponseEnderecoDto enderecoDto) {
    public ResponseLoja(Loja loja) {
        this(loja.getId(), loja.getRazaoSocial(), loja.getNomeFantasia(), loja.getInscricaoEstadual(), loja.getTelefone(), loja.getEmail(), new ResponseEnderecoDto(loja.getEndereco()));
    }
}
