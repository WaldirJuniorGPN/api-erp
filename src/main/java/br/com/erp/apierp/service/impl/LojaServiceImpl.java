package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestEnderecoDto;
import br.com.erp.apierp.dto.request.RequestLoja;
import br.com.erp.apierp.dto.request.RequestLojaAutomatizado;
import br.com.erp.apierp.dto.response.ResponseLoja;
import br.com.erp.apierp.model.Endereco;
import br.com.erp.apierp.model.Loja;
import br.com.erp.apierp.repository.LojaRepository;
import br.com.erp.apierp.service.DataService;
import br.com.erp.apierp.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    private LojaRepository repository;
    @Autowired
    private DataService dataService;

    @Override
    public ResponseEntity<Page<ResponseLoja>> listarTodos(Pageable pageable) {
        var page = this.repository.findAllByAtivoTrue(pageable).map(ResponseLoja::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<ResponseLoja> buscarPorId(Long id) {
        var loja = this.repository.findByIdAndAtivoTrue(id);
        return ResponseEntity.ok(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<ResponseLoja> cadastrar(RequestLojaAutomatizado dados, UriComponentsBuilder uriComponentsBuilder) {
        var json = this.dataService.obterDadosCnpj(dados.cnpj());
        var dto = this.dataService.converteJson(json);
        var jsonEndereco = this.dataService.buscaEnderecoApi(dto.cep());
        var enderecoDto = this.dataService.obterDados(jsonEndereco, RequestEnderecoDto.class);
        var loja = new Loja(dto, dados.cnpj());
        var uri = uriComponentsBuilder.path("/loja/{id}").buildAndExpand(loja.getId()).toUri();
        loja.setEndereco(new Endereco(enderecoDto));
        this.repository.save(loja);
        return ResponseEntity.created(uri).body(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<ResponseLoja> cadastrar(RequestLoja dados, UriComponentsBuilder uriComponentsBuilder) {
        var loja = new Loja(dados);
        var uri = uriComponentsBuilder.path("/loja/{id}").buildAndExpand(loja.getId()).toUri();
        var jsonEndereco = this.dataService.buscaEnderecoApi(dados.enderecoDto().cep());
        var enderecoDto = this.dataService.obterDados(jsonEndereco, RequestEnderecoDto.class);
        loja.setEndereco(new Endereco(enderecoDto));
        this.repository.save(loja);
        return ResponseEntity.created(uri).body(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<ResponseLoja> atualizar(Long id, RequestLoja dados) {
        var loja = this.repository.findByIdAndAtivoTrue(id);
        this.atualizarDados(loja, dados);
        this.repository.save(loja);
        return ResponseEntity.ok(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        var loja = this.repository.getReferenceById(id);
        loja.setAtivo(false);
        this.repository.save(loja);
        return ResponseEntity.noContent().build();
    }

    private void atualizarDados(Loja loja, RequestLoja dados) {
        loja.setCnpj(dados.cnpj());
        loja.setRazaoSocial(dados.razaoSocial());
        loja.setNomeFantasia(dados.nomeFantasia());
        loja.setInscricaoEstadual(dados.inscricaoEstadual());
        loja.setTelefone(dados.telefone());
        loja.setEmail(dados.email());
        loja.setEndereco(new Endereco(dados.enderecoDto()));
    }
}
