package br.com.erp.apierp.controller;

import br.com.erp.apierp.service.ClientePfService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientepf")
public class ClientePfController {

    private final ClientePfService service;

    public ClientePfController(ClientePfService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos(Pageable paginacao) {
        return this.service.buscarTodos(paginacao);
    }
}
