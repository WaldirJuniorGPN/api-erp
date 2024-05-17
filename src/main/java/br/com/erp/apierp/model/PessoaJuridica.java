package br.com.erp.apierp.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class PessoaJuridica extends EntityModel{
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String telefone;
    private String email;
    private Endereco endereco;
    private boolean ativo = true;
}
