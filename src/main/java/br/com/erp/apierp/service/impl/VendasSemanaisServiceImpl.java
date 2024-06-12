package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestVendasDto;
import br.com.erp.apierp.dto.response.ResponseVendasSemanaisDto;
import br.com.erp.apierp.exception.ControllerNotFoundException;
import br.com.erp.apierp.exception.ControllerPropertyReferenceException;
import br.com.erp.apierp.factory.EntityFactory;
import br.com.erp.apierp.model.VendasSemanais;
import br.com.erp.apierp.repository.VendasSemanaisRepository;
import br.com.erp.apierp.service.VendasSemanaisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class VendasSemanaisServiceImpl implements VendasSemanaisService {

    private final VendasSemanaisRepository repository;
    private final EntityFactory<VendasSemanais, RequestVendasDto> factory;

    @Override
    public ResponseEntity<Page<ResponseVendasSemanaisDto>> buscarTodos(Pageable pageable) {
        var page = this.repository.findAllByAtivoTrue(pageable).orElseThrow(
                () -> new ControllerPropertyReferenceException("Parâmetros do Json inadequados")).map(ResponseVendasSemanaisDto::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<ResponseVendasSemanaisDto> buscarPorId(Long id) {
        var vendasSemanais = this.buscarNoBAnco(id);
        return ResponseEntity.ok(new ResponseVendasSemanaisDto(vendasSemanais));
    }

    @Override
    public ResponseEntity<ResponseVendasSemanaisDto> cadastrar(RequestVendasDto dto, UriComponentsBuilder uriComponentsBuilder) {
        var vendasSemanais = this.factory.criar(dto);
        this.repository.save(vendasSemanais);
        var uri = uriComponentsBuilder.path("/vendas-semanais/{id}").buildAndExpand(vendasSemanais.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseVendasSemanaisDto(vendasSemanais));
    }

    @Override
    public ResponseEntity<ResponseVendasSemanaisDto> atualizar(Long id, RequestVendasDto dto) {
        var vendasSemanais = this.buscarNoBAnco(id);
        this.factory.atualizar(vendasSemanais, dto);
        this.repository.save(vendasSemanais);
        return ResponseEntity.ok(new ResponseVendasSemanaisDto(vendasSemanais));
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        var vendasSemanais = this.buscarNoBAnco(id);
        vendasSemanais.setAtivo(false);
        this.repository.save(vendasSemanais);
        return ResponseEntity.noContent().build();
    }

    private ControllerNotFoundException throwVendasNotFoundException() {
        return new ControllerNotFoundException("Vendas não encontrada");
    }

    private VendasSemanais buscarNoBAnco(Long id) {
        return this.repository.findByIdAndAtivoTrue(id).orElseThrow(this::throwVendasNotFoundException);
    }

}
