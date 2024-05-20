package br.com.erp.apierp.service;

import br.com.erp.apierp.dto.request.RequestLoja;
import br.com.erp.apierp.dto.request.RequestLojaAutomatizado;
import br.com.erp.apierp.dto.response.ResponseLoja;
import br.com.erp.apierp.dto.response.ResponseLojaBuscaSimples;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface LojaService {

    ResponseEntity<Page<ResponseLoja>> listarTodos(Pageable pageable);

    ResponseEntity<Page<ResponseLojaBuscaSimples>> listarTodosBuscaSimples(Pageable pageable);

    ResponseEntity<ResponseLoja> buscarPorId(Long id);

    ResponseEntity<ResponseLoja> cadastrar(RequestLojaAutomatizado dados, UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity<ResponseLoja> cadastrar(RequestLoja dados, UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity<ResponseLoja> atualizar(Long id, RequestLoja dados);

    ResponseEntity<Void> deletar(Long id);
}
