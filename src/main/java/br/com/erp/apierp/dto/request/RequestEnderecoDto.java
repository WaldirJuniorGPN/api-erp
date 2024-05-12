package br.com.erp.apierp.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RequestEnderecoDto(
        @NotBlank
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf,
        String ibge,
        String gia,
        String ddd,
        String siafi

) {
}
