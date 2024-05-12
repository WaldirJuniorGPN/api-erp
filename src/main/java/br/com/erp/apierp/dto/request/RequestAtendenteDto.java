package br.com.erp.apierp.dto.request;

import jakarta.validation.Valid;

public record RequestAtendenteDto(
        @Valid
        RequestPessoaDto pessoaDto
) {
}
