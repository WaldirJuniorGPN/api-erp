package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestClientePfDTO;
import br.com.erp.apierp.dto.response.ResponseClientePfDTO;
import br.com.erp.apierp.exception.ControllerNotFoundException;
import br.com.erp.apierp.factory.EntityFactory;
import br.com.erp.apierp.model.ClientePF;
import br.com.erp.apierp.repository.ClientePfRepository;
import br.com.erp.apierp.service.ClientePfService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ClientePfServiceImpl implements ClientePfService {


    private final ClientePfRepository repository;
    private final EntityFactory<ClientePF, RequestClientePfDTO> factory;

    @Override
    public ResponseEntity<Page<ResponseClientePfDTO>> buscarTodos(Pageable pageable) {
        var page = this.repository.findAllByAtivoTrue(pageable).orElseThrow(this::throwClienteNotFoundExeption).map(ResponseClientePfDTO::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> buscarPorId(Long id) {
        var clientePf = this.repository.findByIdAndAtivoTrue(id).orElseThrow(this::throwClienteNotFoundExeption);
        return ResponseEntity.ok(new ResponseClientePfDTO(clientePf));
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> cadastrar(RequestClientePfDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var cliente = this.factory.criar(dados);
        var uri = uriComponentsBuilder.path("/clientepf/{idAtendente}").buildAndExpand(cliente.getId()).toUri();
        this.repository.save(cliente);
        return ResponseEntity.created(uri).body(new ResponseClientePfDTO(cliente));
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> atualizar(RequestClientePfDTO dados, Long id) {
        var cliente = this.repository.findByIdAndAtivoTrue(id).orElseThrow(this::throwClienteNotFoundExeption);
        this.factory.atualizar(cliente, dados);
        this.repository.save(cliente);
        return ResponseEntity.ok(new ResponseClientePfDTO(cliente));
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        var cliente = this.repository.findById(id).orElseThrow(this::throwClienteNotFoundExeption);
        cliente.setAtivo(false);
        this.repository.save(cliente);
        return ResponseEntity.noContent().build();
    }

    private ControllerNotFoundException throwClienteNotFoundExeption() {
        return new ControllerNotFoundException("Cliente não encontrado");
    }
}
