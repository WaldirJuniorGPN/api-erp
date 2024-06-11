package br.com.erp.apierp.factory.impl;

import br.com.erp.apierp.dto.request.RequestCalculadoraDto;
import br.com.erp.apierp.factory.EntityFactory;
import br.com.erp.apierp.model.CalculadoraDeGratificacao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("calculadoraDeGratificacaoFactory")
public class CalculadoraDeGratificacaoFactoryImpl implements EntityFactory<CalculadoraDeGratificacao, RequestCalculadoraDto> {

    @Override
    public CalculadoraDeGratificacao criar(RequestCalculadoraDto dto) {
        var calculadora = new CalculadoraDeGratificacao();
        calculadora.setNome(dto.nome());
        calculadora.setPercentualPrimeiroColocado(dto.percentualPrimeiroColocado());
        calculadora.setPercentualSegundoColocado(dto.percentualSegundoColocado());
        calculadora.setPercentualTerceiroColocado(dto.percentualTerceiroColocado());
        calculadora.setBonusPrimeiroColocado(dto.bonusPrimeiroColocado());
        calculadora.setBonusSegundoColocado(dto.bonusSegundoColocado());
        return calculadora;
    }

    @Override
    public void atualizar(CalculadoraDeGratificacao calculadora, RequestCalculadoraDto dto) {
        if (!calculadora.getNome().equals(dto.nome())) {
            calculadora.setNome(dto.nome());
        }
        if (!calculadora.getPercentualPrimeiroColocado().equals(dto.percentualPrimeiroColocado())) {
            calculadora.setPercentualPrimeiroColocado(dto.percentualPrimeiroColocado());
        }
        if (!calculadora.getPercentualSegundoColocado().equals(dto.percentualSegundoColocado())) {
            calculadora.setPercentualSegundoColocado(dto.percentualSegundoColocado());
        }
        if (!calculadora.getPercentualTerceiroColocado().equals(dto.percentualTerceiroColocado())) {
            calculadora.setPercentualTerceiroColocado(dto.percentualTerceiroColocado());
        }
        if (!calculadora.getBonusPrimeiroColocado().equals(dto.bonusPrimeiroColocado())) {
            calculadora.setBonusPrimeiroColocado(dto.bonusPrimeiroColocado());
        }
        if (!calculadora.getBonusSegundoColocado().equals(dto.bonusSegundoColocado())) {
            calculadora.setBonusSegundoColocado(dto.bonusSegundoColocado());
        }
    }
}
