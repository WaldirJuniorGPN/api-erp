package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestClientePfDTO;
import br.com.erp.apierp.dto.response.ResponseClientePfDTO;
import br.com.erp.apierp.model.ClientePF;
import br.com.erp.apierp.repository.ClientePfRepository;
import br.com.erp.apierp.service.ClientePfService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClientePfServiceImpl implements ClientePfService {


    @Autowired
    private ClientePfRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Page<ResponseClientePfDTO>> buscarTodos(Pageable pageable) {
        var page = this.repository.findAll(pageable);
        var pageDto = page.map(entidade -> this.modelMapper.map(entidade, ResponseClientePfDTO.class));
        return ResponseEntity.ok(pageDto);
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> buscarPorId(Long id) {
        var clientePf = this.repository.getReferenceById(id);
        var dto = this.modelMapper.map(clientePf, ResponseClientePfDTO.class);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<RequestClientePfDTO> cadastrar(RequestClientePfDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var cliente = this.modelMapper.map(dados, ClientePF.class);
        var uri = uriComponentsBuilder.path("/clientepf/{id}").buildAndExpand(cliente.getId()).toUri();
        var dto = this.modelMapper.map(cliente, RequestClientePfDTO.class);
        return ResponseEntity.created(uri).body(dto);
    }

    @Override
    public ResponseEntity<ResponseClientePfDTO> atualizar(RequestClientePfDTO dados, Long id) {
        var cliente = this.repository.getReferenceById(id);
        this.modelMapper.map(dados, cliente);
        this.repository.save(cliente);
        var dto = this.modelMapper.map(cliente, ResponseClientePfDTO.class);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
