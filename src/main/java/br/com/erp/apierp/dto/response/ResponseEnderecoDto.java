package br.com.erp.apierp.dto.response;

public record ResponseEnderecoDto(String cep, String logradouro, String bairro, String localidade, String uf, String ibge, String gia, String ddd, String siafi) {
}
