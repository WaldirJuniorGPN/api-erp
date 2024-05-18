package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.ConverteLojaDto;
import br.com.erp.apierp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private IConverteDados converteDados;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private ConverteJsonService converteJsonService;
    @Autowired
    private CNPJService cnpjService;

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        return this.converteDados.obterDados(json, classe);
    }

    @Override
    public <R extends Record, T> T obterDados(R dto, Class<T> classe) {
        return this.converteDados.obterDados(dto, classe);
    }

    @Override
    public String buscaEnderecoApi(String cep) {
        return this.enderecoService.buscaEndereco(cep);
    }

    @Override
    public ConverteLojaDto converteJson(String json) {
        return this.converteJsonService.converterJson(json);
    }

    @Override
    public String obterDadosCnpj(String cnpj) {
        return this.cnpjService.obterDadosCnpj(cnpj);
    }
}
