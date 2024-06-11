package br.com.erp.apierp.controller;

import br.com.erp.apierp.dto.request.RequestCalculadoraDto;
import br.com.erp.apierp.dto.response.ResponseCalculadoraDto;
import br.com.erp.apierp.repository.CalculadoraDeGratificacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/calculadora")
@RequiredArgsConstructor
public class CalculadoraController {

    private final CalculadoraDeGratificacaoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseCalculadoraDto> cadastrar(@Valid @RequestBody RequestCalculadoraDto dto, UriComponentsBuilder uriComponentsBuilder) {
        return this.service.cadastrar(dto, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseCalculadoraDto>> buscarTodos(Pageable pageable) {
        return this.service.listar(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCalculadoraDto> buscarPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseCalculadoraDto> atualizar(@PathVariable Long id, @Valid @RequestBody RequestCalculadoraDto dto) {
        return this.service.atualizar(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return this.service.deletar(id);
    }
}
