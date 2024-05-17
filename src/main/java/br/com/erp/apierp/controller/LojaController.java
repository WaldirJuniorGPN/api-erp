package br.com.erp.apierp.controller;

import br.com.erp.apierp.dto.request.RequestLoja;
import br.com.erp.apierp.dto.request.RequestLojaAutomatizado;
import br.com.erp.apierp.dto.response.ResponseLoja;
import br.com.erp.apierp.service.LojaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/loja")
public class LojaController {

    @Autowired
    private LojaService service;

    @GetMapping
    public ResponseEntity<Page<ResponseLoja>> listarTodos(Pageable pageable) {
        return this.service.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseLoja> buscarPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseLoja> cadastrar(@RequestBody @Valid RequestLoja dados, UriComponentsBuilder uriComponentsBuilder) {
        return this.service.cadastrar(dados, uriComponentsBuilder);
    }

    @PostMapping("/automatizado")
    @Transactional
    public ResponseEntity<ResponseLoja> cadastrar(@RequestBody @Valid RequestLojaAutomatizado dados, UriComponentsBuilder uriComponentsBuilder) {
        return this.service.cadastrar(dados, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseLoja> atualizar(@PathVariable Long id, @Valid RequestLoja dados) {
        return this.service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return this.service.deletar(id);
    }
}
