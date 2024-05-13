package br.com.erp.apierp.service;

import br.com.erp.apierp.dto.request.RequestAtendenteDto;
import br.com.erp.apierp.dto.request.RequestVendasDto;
import br.com.erp.apierp.dto.response.ResponseAtendenteDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface AtendenteService {

    ResponseEntity<Page<ResponseAtendenteDto>> listarTodos(Pageable pageable);
    ResponseEntity<ResponseAtendenteDto> buscarPorId(Long id);
    ResponseEntity<ResponseAtendenteDto> cadastrar(@Valid RequestAtendenteDto dados, UriComponentsBuilder uriComponentsBuilder);
    ResponseEntity<ResponseAtendenteDto> cadastrarVendasSemanais(RequestVendasDto vendasDto);
    ResponseEntity<ResponseAtendenteDto> atualizar(Long id, RequestAtendenteDto dados);
    ResponseEntity<Void> deletar(Long id);
}
