package br.com.erp.apierp.factory;

import br.com.erp.apierp.dto.request.RequestEnderecoDto;
import br.com.erp.apierp.dto.request.RequestLoja;
import br.com.erp.apierp.dto.request.RequestLojaAutomatizado;
import br.com.erp.apierp.model.Endereco;
import br.com.erp.apierp.model.Loja;
import br.com.erp.apierp.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LojaFactory {

    @Autowired
    private DataService service;

    public Loja criaLojaApiCnpj(RequestLojaAutomatizado dados) {
        var json = this.service.obterDadosCnpj(dados.cnpj());
        var lojaDto = this.service.converteJson(json);
        var jsonEndereco = this.service.buscaEnderecoApi(lojaDto.cep());
        var enderecoDto = this.service.obterDados(jsonEndereco, RequestEnderecoDto.class);
        var loja = new Loja(lojaDto, dados.cnpj());
        loja.setEndereco(new Endereco(enderecoDto));
        return loja;
    }

    public Loja criaLoja(RequestLoja dados) {
        var loja = new Loja(dados);
        var jsonEndereco = this.service.buscaEnderecoApi(dados.enderecoDto().cep());
        var enderecoDto = this.service.obterDados(jsonEndereco, RequestEnderecoDto.class);
        loja.setEndereco(new Endereco(enderecoDto));
        return loja;
    }

    public void atualizaLoja(Loja loja, RequestLoja dto) {
        loja.setCnpj(dto.cnpj());
        loja.setRazaoSocial(dto.razaoSocial());
        loja.setNomeFantasia(dto.nomeFantasia());
        loja.setInscricaoEstadual(dto.inscricaoEstadual());
        loja.setTelefone(dto.telefone());
        loja.setEmail(dto.email());
        loja.setEndereco(new Endereco(dto.enderecoDto()));
    }

}
