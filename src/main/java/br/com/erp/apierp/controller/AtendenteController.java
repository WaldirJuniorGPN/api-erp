package br.com.erp.apierp.controller;

import br.com.erp.apierp.dto.request.RequestAtendenteDto;
import br.com.erp.apierp.dto.request.RequestVendasDto;
import br.com.erp.apierp.dto.request.RequestVinculoLojaDto;
import br.com.erp.apierp.dto.response.ResponseAtendenteDto;
import br.com.erp.apierp.service.AtendenteService;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/atendente")
@RequiredArgsConstructor
public class AtendenteController {

    private final AtendenteService service;

    @GetMapping
    public ResponseEntity<Page<ResponseAtendenteDto>> listarTodos(Pageable pageable) {
        return this.service.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAtendenteDto> buscarPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }

    @PostMapping
    @Transient
    public ResponseEntity<ResponseAtendenteDto> cadastrar(@RequestBody @Valid RequestAtendenteDto dados, UriComponentsBuilder uriComponentsBuilder) {
        return this.service.cadastrar(dados, uriComponentsBuilder);
    }

    @PatchMapping("/cadastro-vendas")
    @Transient
    public ResponseEntity<ResponseAtendenteDto> cadastrarVendasSemanais(@RequestBody @Valid RequestVendasDto vendasDto) {
        return this.service.cadastrarVendasSemanais(vendasDto);
    }

    @PatchMapping("/vinculo-loja")
    @Transient
    public ResponseEntity<ResponseAtendenteDto> vincularAtendenteALoja(@RequestBody @Valid RequestVinculoLojaDto dto){
        return  this.service.vincularAtendenteALoja(dto);
    }

    @PutMapping("/{id}")
    @Transient
    public ResponseEntity<ResponseAtendenteDto> atualizar(@PathVariable Long id, @RequestBody @Valid RequestAtendenteDto dados) {
        return this.service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @Transient
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return this.service.deletar(id);
    }
}
