package br.com.erp.apierp.controller;

import br.com.erp.apierp.dto.request.RequestAtendenteDto;
import br.com.erp.apierp.dto.request.RequestVendasDto;
import br.com.erp.apierp.dto.response.ResponseAtendenteDto;
import br.com.erp.apierp.service.AtendenteService;
import br.com.erp.apierp.service.impl.AtendenteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/atendente")
public class AtendenteController {

    @Autowired
    private AtendenteServiceImpl service;

    @GetMapping
    public ResponseEntity<Page<ResponseAtendenteDto>> listarTodos(Pageable pageable) {
        return this.service.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAtendenteDto> buscarPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<ResponseAtendenteDto> cadastrar(@Valid RequestAtendenteDto dados, UriComponentsBuilder uriComponentsBuilder) {
        return this.service.cadastrar(dados, uriComponentsBuilder);
    }

    @PostMapping("/cadastro-vendas")
    public ResponseEntity<ResponseAtendenteDto> cadastrarVendasSemanais(@Valid RequestVendasDto vendasDto) {
        return this.service.cadastrarVendasSemanais(vendasDto);
    }

    @PutMapping
    public ResponseEntity<ResponseAtendenteDto> atualizar(Long id, @Valid RequestAtendenteDto dados) {
        return this.service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return this.service.deletar(id);
    }
}
