package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.ConverteLojaDto;
import br.com.erp.apierp.dto.request.RequestEnderecoDto;
import br.com.erp.apierp.dto.request.RequestLoja;
import br.com.erp.apierp.dto.request.RequestLojaAutomatizado;
import br.com.erp.apierp.dto.response.ResponseLoja;
import br.com.erp.apierp.model.Endereco;
import br.com.erp.apierp.model.Loja;
import br.com.erp.apierp.repository.LojaRepository;
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
    private CNPJService cnpjService;
    @Autowired
    private ConverteDadosImpl converteDados;
    @Autowired
    private EnderecoService enderecoService;

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
        var json = this.cnpjService.obterDadosCnpj(dados.cnpj());
        var dto = this.converteDados.obterDados(json, ConverteLojaDto.class);
        System.out.println(dto.cep());
        System.out.println(dto);
        var jsonEndereco = this.enderecoService.buscaEndereco(dto.cep());
        System.out.println(jsonEndereco);
        var enderecoDto = this.converteDados.obterDados(jsonEndereco, RequestEnderecoDto.class);
        var loja = new Loja(dto, dados.cnpj());
        loja.setEndereco(new Endereco(enderecoDto));
        var uri = uriComponentsBuilder.path("/loja/{id}").buildAndExpand(loja.getId()).toUri();
        this.repository.save(loja);
        return ResponseEntity.created(uri).body(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<ResponseLoja> cadastrar(RequestLoja dados, UriComponentsBuilder uriComponentsBuilder) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseLoja> atualizar(Long id, RequestLoja dados) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        return null;
    }
}
