package br.com.erp.apierp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestLojaAutomatizado(
        @NotBlank
        @Pattern(regexp = "^\\d{14}$")
        String cnpj
) {
}
