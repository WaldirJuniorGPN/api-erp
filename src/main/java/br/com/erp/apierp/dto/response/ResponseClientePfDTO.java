package br.com.erp.apierp.dto.response;

import br.com.erp.apierp.model.ClientePF;
import br.com.erp.apierp.model.Endereco;

public record ResponseClientePfDTO(Long id, String nome, String cpf, String email, String telefone, Endereco endereco) {
    public ResponseClientePfDTO(ClientePF cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getTelefone(), cliente.getEndereco());
    }
}
