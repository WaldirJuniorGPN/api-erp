package br.com.erp.apierp.service;

import br.com.erp.apierp.dto.request.RequestVendasDto;
import br.com.erp.apierp.dto.response.ResponseVendasSemanaisDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface VendasSemanaisService {

    ResponseEntity<Page<ResponseVendasSemanaisDto>> buscarTodos(Pageable pageable);

    ResponseEntity<ResponseVendasSemanaisDto> buscarPorId(Long id);

    ResponseEntity<ResponseVendasSemanaisDto> cadastrar(RequestVendasDto dto, UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity<ResponseVendasSemanaisDto> atualizar(Long id, RequestVendasDto dto);

    ResponseEntity<Void> deletar(Long id);
}
