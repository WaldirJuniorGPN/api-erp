package br.com.erp.apierp.controller;

import br.com.erp.apierp.dto.request.RequestClientePfDTO;
import br.com.erp.apierp.dto.response.ResponseClientePfDTO;
import br.com.erp.apierp.service.ClientePfService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientepf")
@RequiredArgsConstructor
public class ClientePfController {

    private final ClientePfService service;

    @GetMapping
    public ResponseEntity<Page<ResponseClientePfDTO>> buscarTodos(Pageable paginacao) {
        return this.service.buscarTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClientePfDTO> buscarPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseClientePfDTO> cadastrar(RequestClientePfDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        return this.service.cadastrar(dados, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseClientePfDTO> atualizar(@PathVariable Long id, RequestClientePfDTO dados) {
        return this.service.atualizar(dados, id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return this.service.deletar(id);
    }
}
