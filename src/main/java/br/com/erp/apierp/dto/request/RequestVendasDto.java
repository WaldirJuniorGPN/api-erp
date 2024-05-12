package br.com.erp.apierp.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestVendasDto(
        @NotNull
        Long idAtendente,
        @DecimalMin(value = "0.00", inclusive = true)
        BigDecimal vendasPrimeiraSemana,
        @DecimalMin(value = "0.00", inclusive = true)
        BigDecimal vendasSegundaSemana,
        @DecimalMin(value = "0.00", inclusive = true)
        BigDecimal vendasTerceiraSemana,
        @DecimalMin(value = "0.00", inclusive = true)
        BigDecimal vendasQuartaSemana,
        @DecimalMin(value = "0.00", inclusive = true)
        BigDecimal vendasQuintaSemana,
        @DecimalMin(value = "0.00", inclusive = true)
        BigDecimal vendasSextaSemana
) {
}
