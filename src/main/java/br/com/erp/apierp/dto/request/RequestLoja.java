package br.com.erp.apierp.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestLoja(
        @NotBlank
        @Pattern(regexp = "^\\d{14}$")
        String cnpj,
        @NotBlank
        String razaoSocial,
        String nomeFantasia,
        @Pattern(regexp = "^\\d{2,}\\.\\d{3,}\\.\\d{3,}\\/\\d{4,}$")
        String inscricaoEstadual,
        @Pattern(regexp = "^\\(\\d{2}\\)\\d{4,5}-\\d{4}$")
        String telefone,
        @Email
        String email,
        @Valid
        RequestEnderecoDto enderecoDto
) {
}
