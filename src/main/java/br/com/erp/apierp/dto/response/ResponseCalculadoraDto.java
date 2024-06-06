package br.com.erp.apierp.dto.response;

import br.com.erp.apierp.model.CalculadoraDeGratificacao;

import java.math.BigDecimal;

public record ResponseCalculadoraDto(Long id,
                                     String nome,
                                     BigDecimal percentualPrimeiroColocado,
                                     BigDecimal percentualSegundoColocado,
                                     BigDecimal percentualTerceiroColocado,
                                     BigDecimal bonusPrimeiroColocado,
                                     BigDecimal bonusSegundoColocado) {
    public ResponseCalculadoraDto(CalculadoraDeGratificacao calculadora) {
        this(calculadora.getId(),
                calculadora.getNome(),
                calculadora.getPercentualPrimeiroColocado(),
                calculadora.getPercentualSegundoColocado(),
                calculadora.getPercentualTerceiroColocado(),
                calculadora.getBonusPrimeiroColocado(),
                calculadora.getBonusSegundoColocado());
    }
}
