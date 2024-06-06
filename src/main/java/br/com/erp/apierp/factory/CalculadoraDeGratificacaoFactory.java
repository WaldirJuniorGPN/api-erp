package br.com.erp.apierp.factory;

import br.com.erp.apierp.dto.request.RequestCalculadoraDto;
import br.com.erp.apierp.model.CalculadoraDeGratificacao;

public interface CalculadoraDeGratificacaoFactory {

    CalculadoraDeGratificacao cadastraCalculadora(RequestCalculadoraDto dto);

    void atualizaCalculadora(CalculadoraDeGratificacao calculadora, RequestCalculadoraDto dto);
}
