package br.com.erp.apierp.dto.request;

import jakarta.validation.constraints.NotNull;

public record RequestVinculoLojaDto(
        @NotNull
        Long idAtendnete,
        @NotNull
        Long idLoja
) {
}
