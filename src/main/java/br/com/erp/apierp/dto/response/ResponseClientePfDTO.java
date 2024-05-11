package br.com.erp.apierp.dto.response;

import br.com.erp.apierp.model.Endereco;

public record ResponseClientePfDTO(Long id, String nome, String cpf, String email, String telefone, Endereco endereco) {
}
