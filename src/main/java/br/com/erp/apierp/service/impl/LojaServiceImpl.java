package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestLoja;
import br.com.erp.apierp.dto.request.RequestLojaAutomatizado;
import br.com.erp.apierp.dto.response.ResponseLoja;
import br.com.erp.apierp.dto.response.ResponseLojaBuscaSimples;
import br.com.erp.apierp.exception.ControllerNotFoundException;
import br.com.erp.apierp.factory.impl.LojaFactoryImpl;
import br.com.erp.apierp.repository.LojaRepository;
import br.com.erp.apierp.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class LojaServiceImpl implements LojaService {

    private final LojaRepository repository;
    private final LojaFactoryImpl factory;

    @Override
    public ResponseEntity<Page<ResponseLoja>> listarTodos(Pageable pageable) {
        var page = this.repository.findAllByAtivoTrue(pageable).orElseThrow(this::throwLojaNotFoundException).map(ResponseLoja::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<Page<ResponseLojaBuscaSimples>> listarTodosBuscaSimples(Pageable pageable) {
        var page = this.repository.findAllByAtivoTrue(pageable).orElseThrow(this::throwLojaNotFoundException).map(ResponseLojaBuscaSimples::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<ResponseLoja> buscarPorId(Long id) {
        var loja = this.repository.findByIdAndAtivoTrue(id).orElseThrow(this::throwLojaNotFoundException);
        return ResponseEntity.ok(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<ResponseLoja> cadastrar(RequestLojaAutomatizado dados, UriComponentsBuilder uriComponentsBuilder) {
        var loja = this.factory.criaLojaApiCnpj(dados);
        var uri = uriComponentsBuilder.path("/loja/{id}").buildAndExpand(loja.getId()).toUri();
        this.repository.save(loja);
        return ResponseEntity.created(uri).body(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<ResponseLoja> cadastrar(RequestLoja dados, UriComponentsBuilder uriComponentsBuilder) {
        var loja = this.factory.criaLoja(dados);
        var uri = uriComponentsBuilder.path("/loja/{id}").buildAndExpand(loja.getId()).toUri();
        this.repository.save(loja);
        return ResponseEntity.created(uri).body(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<ResponseLoja> atualizar(Long id, RequestLoja dados) {
        var loja = this.repository.findByIdAndAtivoTrue(id).orElseThrow(this::throwLojaNotFoundException);
        this.factory.atualizaLoja(loja, dados);
        this.repository.save(loja);
        return ResponseEntity.ok(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        var loja = this.repository.findById(id).orElseThrow(this::throwLojaNotFoundException);
        loja.setAtivo(false);
        this.repository.save(loja);
        return ResponseEntity.noContent().build();
    }

    private ControllerNotFoundException throwLojaNotFoundException() {
        return new ControllerNotFoundException("Loja não encontrada");
    }
}
