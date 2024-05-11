package br.com.erp.apierp.service;

import br.com.erp.apierp.dto.request.RequestClientePfDTO;
import br.com.erp.apierp.dto.response.ResponseClientePfDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

public interface ClientePfService {

    ResponseEntity<Page<ResponseClientePfDTO>> buscarTodos(Pageable pageable);
    ResponseEntity<ResponseClientePfDTO> buscarPorId(@PathVariable Long id);
    ResponseEntity<RequestClientePfDTO> cadastrar(@Valid RequestClientePfDTO dados, UriComponentsBuilder uriComponentsBuilder);
    ResponseEntity<ResponseClientePfDTO> atualizar(@Valid RequestClientePfDTO dados, Long id);
    ResponseEntity<Void> deletar(@PathVariable Long id);
}
