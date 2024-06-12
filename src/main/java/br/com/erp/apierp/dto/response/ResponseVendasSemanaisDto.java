package br.com.erp.apierp.dto.response;

import br.com.erp.apierp.model.VendasSemanais;

import java.math.BigDecimal;

public record ResponseVendasSemanaisDto(Long id, BigDecimal primeiraSemana, BigDecimal segundaSemana,
                                        BigDecimal terceiraSemana, BigDecimal quartaSemana, BigDecimal quintaSemana,
                                        BigDecimal sextaSemana, BigDecimal total) {
    public ResponseVendasSemanaisDto(VendasSemanais vendas) {
        this(vendas.getId(), vendas.getPrimeiraSemana(), vendas.getSegundaSemana(), vendas.getTerceiraSemana(),
                vendas.getQuartaSemana(), vendas.getQuintaSemana(), vendas.getSextaSemana(), vendas.getAtendente().getVendasTotal());
    }
}
