package br.com.erp.apierp.repository;

import br.com.erp.apierp.dto.request.RequestCalculadoraDto;
import br.com.erp.apierp.dto.response.ResponseCalculadoraDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface CalculadoraDeGratificacaoService {

    ResponseEntity<ResponseCalculadoraDto> cadastrar(RequestCalculadoraDto dto, UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity<ResponseCalculadoraDto> atualizar(RequestCalculadoraDto dto, Long idCalculadora);

    ResponseEntity<Page<ResponseCalculadoraDto>> listar(Pageable pageable);

    ResponseEntity<ResponseCalculadoraDto> buscarPorId(Long idCalculadora);

    ResponseEntity<Void> deletar(Long idCalculadora);
}
