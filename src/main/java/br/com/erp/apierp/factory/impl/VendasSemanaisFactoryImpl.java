package br.com.erp.apierp.factory.impl;

import br.com.erp.apierp.dto.request.RequestVendasDto;
import br.com.erp.apierp.exception.ControllerNotFoundException;
import br.com.erp.apierp.factory.EntityFactory;
import br.com.erp.apierp.model.Atendente;
import br.com.erp.apierp.model.VendasSemanais;
import br.com.erp.apierp.repository.AtendenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("vendasSemanaisFactory")
@RequiredArgsConstructor
public class VendasSemanaisFactoryImpl implements EntityFactory<VendasSemanais, RequestVendasDto> {

    private final AtendenteRepository repository;

    @Override
    public VendasSemanais criar(RequestVendasDto dto) {
        var vendasSemanais = new VendasSemanais();
        vendasSemanais.setPrimeiraSemana(dto.vendasPrimeiraSemana());
        vendasSemanais.setSegundaSemana(dto.vendasSegundaSemana());
        vendasSemanais.setTerceiraSemana(dto.vendasTerceiraSemana());
        vendasSemanais.setQuartaSemana(dto.vendasQuartaSemana());
        vendasSemanais.setQuintaSemana(dto.vendasQuintaSemana());
        vendasSemanais.setSextaSemana(dto.vendasSextaSemana());
        vendasSemanais.setAtendente(this.buscarAtendenteNoBanco(dto.idAtendente()));
        return vendasSemanais;
    }

    @Override
    public void atualizar(VendasSemanais vendas, RequestVendasDto dto) {
        if (!vendas.getAtendente().getId().equals(dto.idAtendente())) {
            vendas.setAtendente(this.buscarAtendenteNoBanco(dto.idAtendente()));
        }
        if (!vendas.getPrimeiraSemana().equals(dto.vendasPrimeiraSemana())) {
            vendas.setPrimeiraSemana(dto.vendasPrimeiraSemana());
        }
        if (!vendas.getSegundaSemana().equals(dto.vendasSegundaSemana())) {
            vendas.setSegundaSemana(dto.vendasSegundaSemana());
        }
        if (!vendas.getTerceiraSemana().equals(dto.vendasTerceiraSemana())) {
            vendas.setTerceiraSemana(dto.vendasTerceiraSemana());
        }
        if (!vendas.getQuartaSemana().equals(dto.vendasQuartaSemana())) {
            vendas.setQuartaSemana(dto.vendasQuartaSemana());
        }
        if (!vendas.getQuintaSemana().equals(dto.vendasQuintaSemana())) {
            vendas.setQuintaSemana(dto.vendasQuintaSemana());
        }
        if (!vendas.getSextaSemana().equals(dto.vendasSextaSemana())) {
            vendas.setSextaSemana(dto.vendasSextaSemana());
        }
    }

    private Atendente buscarAtendenteNoBanco(Long id) {
        return this.repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new ControllerNotFoundException("Atendente não encontrado"));
    }
}
