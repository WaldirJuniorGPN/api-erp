package br.com.erp.apierp.factory.impl;

import br.com.erp.apierp.dto.request.RequestAtendenteDto;
import br.com.erp.apierp.dto.request.RequestEnderecoDto;
import br.com.erp.apierp.factory.EntityFactory;
import br.com.erp.apierp.model.Atendente;
import br.com.erp.apierp.model.Endereco;
import br.com.erp.apierp.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("atendenteFactory")
@RequiredArgsConstructor
public class AtendenteFactoryImpl implements EntityFactory<Atendente, RequestAtendenteDto> {

    private final DataService service;

    @Override
    public Atendente criar(RequestAtendenteDto dados) {
        var jsonEndereco = this.service.buscaEnderecoApi(dados.endereco().cep());
        var endereco = new Endereco(this.service.obterDados(jsonEndereco, RequestEnderecoDto.class));
        var atendente = new Atendente(dados);
        atendente.setEndereco(endereco);
        return atendente;
    }

    @Override
    public void atualizar(Atendente atendente, RequestAtendenteDto dto) {
        atendente.setNome(dto.nome());
        var json = this.service.buscaEnderecoApi(dto.endereco().cep());
        var endereco = new Endereco(this.service.obterDados(json, RequestEnderecoDto.class));
        atendente.setEndereco(endereco);
        atendente.setCpf(dto.cpf());
        atendente.setEmail(dto.email());
        atendente.setTelefone(dto.telefone());
    }
}
