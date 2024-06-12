package br.com.erp.apierp.controller;

import br.com.erp.apierp.dto.request.RequestVendasDto;
import br.com.erp.apierp.dto.response.ResponseVendasSemanaisDto;
import br.com.erp.apierp.service.VendasSemanaisService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vendas-semanais")
@RequiredArgsConstructor
public class VendasSemanaisController {

    private final VendasSemanaisService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseVendasSemanaisDto> cadastrar(@Valid @RequestBody RequestVendasDto dto, UriComponentsBuilder uriComponentsBuilder) {
        return this.service.cadastrar(dto, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseVendasSemanaisDto>> buscarTodos(Pageable pageable) {
        return this.service.buscarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVendasSemanaisDto> buscarPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseVendasSemanaisDto> atualizar(@PathVariable Long id, @Valid @RequestBody RequestVendasDto dto) {
        return this.service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return this.service.deletar(id);
    }
}
