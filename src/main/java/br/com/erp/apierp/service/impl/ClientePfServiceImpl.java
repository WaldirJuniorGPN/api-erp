package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestClientePfDTO;
import br.com.erp.apierp.dto.response.ResponseClientePfDTO;
import br.com.erp.apierp.factory.impl.ClientePfFactoryImpl;
import br.com.erp.apierp.repository.ClientePfRepository;
import br.com.erp.apierp.service.ClientePfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClientePfServiceImpl implements ClientePfService {


    private final ClientePfRepository repository;
    private final ClientePfFactoryImpl factory;

    @Autowired
    public ClientePfServiceImpl(ClientePfRepository repository, ClientePfFactoryImpl factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public ResponseEntity<Page<ResponseClientePfDTO>> buscarTodos(Pageable pageable) {
        var page = this.repository.findAllByAtivoTrue(pageable).map(ResponseClientePfDTO::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> buscarPorId(Long id) {
        var clientePf = this.repository.findByIdAndAtivoTrue(id);
        return ResponseEntity.ok(new ResponseClientePfDTO(clientePf));
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> cadastrar(RequestClientePfDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var cliente = this.factory.criaCliente(dados);
        var uri = uriComponentsBuilder.path("/clientepf/{idAtendente}").buildAndExpand(cliente.getId()).toUri();
        this.repository.save(cliente);
        return ResponseEntity.created(uri).body(new ResponseClientePfDTO(cliente));
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> atualizar(RequestClientePfDTO dados, Long id) {
        var cliente = this.repository.findByIdAndAtivoTrue(id);
        this.factory.atualizaCliente(cliente, dados);
        this.repository.save(cliente);
        return ResponseEntity.ok(new ResponseClientePfDTO(cliente));
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        var cliente = this.repository.getReferenceById(id);
        cliente.setAtivo(false);
        this.repository.save(cliente);
        return ResponseEntity.noContent().build();
    }
}
