package br.com.erp.apierp.dto.response;

import br.com.erp.apierp.model.Endereco;

public record ResponseEnderecoDto(String cep, String logradouro, String bairro, String localidade, String uf,
                                  String ibge, String ddd, String siafi) {
    public ResponseEnderecoDto(Endereco endereco) {
        this(endereco.getCep(), endereco.getLogradouro(), endereco.getBairro(), endereco.getLocalidade(), endereco.getUf(), endereco.getIbge(), endereco.getDdd(), endereco.getSiafi());
    }
}
