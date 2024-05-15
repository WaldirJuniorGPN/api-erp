package br.com.erp.apierp.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Pessoa extends EntityModel {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    @Embedded
    private Endereco endereco;
    private boolean ativo = true;
}
