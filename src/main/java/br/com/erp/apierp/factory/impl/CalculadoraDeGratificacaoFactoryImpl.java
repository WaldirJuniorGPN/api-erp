package br.com.erp.apierp.factory.impl;

import br.com.erp.apierp.dto.request.RequestCalculadoraDto;
import br.com.erp.apierp.factory.CalculadoraDeGratificacaoFactory;
import br.com.erp.apierp.model.CalculadoraDeGratificacao;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraDeGratificacaoFactoryImpl implements CalculadoraDeGratificacaoFactory {

    @Override
    public CalculadoraDeGratificacao cadastraCalculadora(RequestCalculadoraDto dto) {
        var calculadora = new CalculadoraDeGratificacao();

        return calculadora;
    }

    @Override
    public void atualizaCalculadora(CalculadoraDeGratificacao calculadora, RequestCalculadoraDto dto) {

    }
}
