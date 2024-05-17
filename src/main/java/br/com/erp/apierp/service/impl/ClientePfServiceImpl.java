package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestClientePfDTO;
import br.com.erp.apierp.dto.request.RequestEnderecoDto;
import br.com.erp.apierp.dto.response.ResponseClientePfDTO;
import br.com.erp.apierp.model.ClientePF;
import br.com.erp.apierp.model.Endereco;
import br.com.erp.apierp.repository.ClientePfRepository;
import br.com.erp.apierp.service.ClientePfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClientePfServiceImpl implements ClientePfService {


    @Autowired
    private ClientePfRepository repository;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private ConverteDadosImpl converteDados;

    @Override
    public ResponseEntity<Page<ResponseClientePfDTO>> buscarTodos(Pageable pageable) {
        var page = this.repository.findAllByAtivoTrue(pageable).map(ResponseClientePfDTO::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> buscarPorId(Long id) {
        var clientePf = this.repository.findByIdAndAtivoTrue(id);
        return ResponseEntity.ok(new ResponseClientePfDTO(clientePf));
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> cadastrar(RequestClientePfDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var cliente = new ClientePF(dados);
        var endereco = this.enderecoService.buscaEndereco(dados.pessoaDto().endereco().cep());
        var enderecoDto = this.converteDados.obterDados(endereco, RequestEnderecoDto.class);
        var uri = uriComponentsBuilder.path("/clientepf/{idAtendente}").buildAndExpand(cliente.getId()).toUri();
        cliente.setEndereco(new Endereco(enderecoDto));
        this.repository.save(cliente);
        return ResponseEntity.created(uri).body(new ResponseClientePfDTO(cliente));
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> atualizar(RequestClientePfDTO dados, Long id) {
        var cliente = this.repository.findByIdAndAtivoTrue(id);
        this.atualizarDados(cliente, dados);
        this.repository.save(cliente);
        return ResponseEntity.ok(new ResponseClientePfDTO(cliente));
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        var cliente = this.repository.getReferenceById(id);
        cliente.setAtivo(false);
        this.repository.save(cliente);
        return ResponseEntity.noContent().build();
    }

    private void atualizarDados(ClientePF cliente, RequestClientePfDTO dados) {
        cliente.setNome(dados.pessoaDto().nome());
        cliente.setCpf(dados.pessoaDto().cpf());
        cliente.setEmail(dados.pessoaDto().email());
        cliente.setTelefone(dados.pessoaDto().telefone());
        var json = this.enderecoService.buscaEndereco(dados.pessoaDto().endereco().cep());
        var enderecoDto = this.converteDados.obterDados(json, RequestEnderecoDto.class);
        cliente.setEndereco(new Endereco(enderecoDto));
    }
}
