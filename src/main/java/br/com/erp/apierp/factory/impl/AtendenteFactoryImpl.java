package br.com.erp.apierp.factory.impl;

import br.com.erp.apierp.dto.request.RequestAtendenteDto;
import br.com.erp.apierp.dto.request.RequestEnderecoDto;
import br.com.erp.apierp.factory.AtendenteFactory;
import br.com.erp.apierp.model.Atendente;
import br.com.erp.apierp.model.Endereco;
import br.com.erp.apierp.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtendenteFactoryImpl implements AtendenteFactory {

    private final DataService service;

    @Autowired
    public AtendenteFactoryImpl(DataService service) {
        this.service = service;
    }

    @Override
    public Atendente criaAtendente(RequestAtendenteDto dados) {
        var jsonEndereco = this.service.buscaEnderecoApi(dados.endereco().cep());
        var endereco = new Endereco(this.service.obterDados(jsonEndereco, RequestEnderecoDto.class));
        var atendente = new Atendente(dados);
        atendente.setEndereco(endereco);
        return atendente;
    }

    @Override
    public void atualizaAtendente(Atendente atendente, RequestAtendenteDto dto) {
        atendente.setNome(dto.nome());
        var json = this.service.buscaEnderecoApi(dto.endereco().cep());
        var endereco = new Endereco(this.service.obterDados(json, RequestEnderecoDto.class));
        atendente.setEndereco(endereco);
        atendente.setCpf(dto.cpf());
        atendente.setEmail(dto.email());
        atendente.setTelefone(dto.telefone());
    }
}
