package br.com.erp.apierp.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Setter;

@MappedSuperclass
public abstract class Pessoa extends EntityModel {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    @Embedded
    @Setter
    private Endereco endereco;
}
