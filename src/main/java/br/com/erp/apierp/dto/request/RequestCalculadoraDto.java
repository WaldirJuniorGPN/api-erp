package br.com.erp.apierp.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestCalculadoraDto(
        @NotBlank
        String nome,
        @NotNull
        @Min(0)
        BigDecimal percentualPrimeiroColocado,
        @NotNull
        @Min(0)
        BigDecimal percentualSegundoColocado,
        @NotNull
        @Min(0)
        BigDecimal percentualTerceiroColocado,
        @NotNull
        @Min(0)
        BigDecimal bonusPrimeiroColocado,
        @NotNull
        @Min(0)
        BigDecimal bonusSegundoColocado,
        Long idLoja
) {
}
