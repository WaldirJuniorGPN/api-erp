package br.com.erp.apierp.factory;

import br.com.erp.apierp.dto.request.RequestClientePfDTO;
import br.com.erp.apierp.dto.request.RequestEnderecoDto;
import br.com.erp.apierp.model.ClientePF;
import br.com.erp.apierp.model.Endereco;
import br.com.erp.apierp.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientePfFactory {

    private final DataService service;

    @Autowired
    public ClientePfFactory(DataService service) {
        this.service = service;
    }

    public ClientePF criaCliente(RequestClientePfDTO dados) {
        var cliente = new ClientePF(dados);
        var jsonEndereco = this.service.buscaEnderecoApi(dados.pessoaDto().endereco().cep());
        var enderecoDto = this.service.obterDados(jsonEndereco, RequestEnderecoDto.class);
        cliente.setEndereco(new Endereco(enderecoDto));
        return cliente;
    }

    public void atualizaCliente(ClientePF cliente, RequestClientePfDTO dto) {
        cliente.setNome(dto.pessoaDto().nome());
        cliente.setCpf(dto.pessoaDto().cpf());
        cliente.setEmail(dto.pessoaDto().email());
        cliente.setTelefone(dto.pessoaDto().telefone());
        var json = this.service.buscaEnderecoApi(dto.pessoaDto().endereco().cep());
        var enderecoDto = this.service.obterDados(json, RequestEnderecoDto.class);
        cliente.setEndereco(new Endereco(enderecoDto));
    }
}
