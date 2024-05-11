package br.com.erp.apierp.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String cep;
    private String ibge;
    private String ddd;
    private String siafi;
}
