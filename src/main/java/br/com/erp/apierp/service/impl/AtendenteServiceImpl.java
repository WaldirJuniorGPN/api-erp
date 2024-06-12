package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestAtendenteDto;
import br.com.erp.apierp.dto.request.RequestVendasDto;
import br.com.erp.apierp.dto.request.RequestVinculoLojaDto;
import br.com.erp.apierp.dto.response.ResponseAtendenteDto;
import br.com.erp.apierp.exception.ControllerNotFoundException;
import br.com.erp.apierp.factory.EntityFactory;
import br.com.erp.apierp.factory.impl.AtendenteFactoryImpl;
import br.com.erp.apierp.model.Atendente;
import br.com.erp.apierp.model.VendasSemanais;
import br.com.erp.apierp.repository.AtendenteRepository;
import br.com.erp.apierp.repository.LojaRepository;
import br.com.erp.apierp.repository.VendasSemanaisRepository;
import br.com.erp.apierp.service.AtendenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class AtendenteServiceImpl implements AtendenteService {

    private final AtendenteRepository atendenteRepository;
    private final VendasSemanaisRepository vendasSemanaisRepository;
    private final LojaRepository lojaRepository;
    private final EntityFactory<Atendente, RequestAtendenteDto> factory;

    @Override
    public ResponseEntity<Page<ResponseAtendenteDto>> listarTodos(Pageable pageable) {
        var page = this.atendenteRepository.findAllByAtivoTrue(pageable).orElseThrow().map(ResponseAtendenteDto::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> buscarPorId(Long id) {
        var atendente = this.atendenteRepository.findByIdAndAtivoTrue(id).orElseThrow(this::throwAtendenteNotFoundException);
        var dto = new ResponseAtendenteDto(atendente);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> cadastrar(@Valid RequestAtendenteDto dados, UriComponentsBuilder uriComponentsBuilder) {
        var atendente = this.factory.criar(dados);
        this.atendenteRepository.save(atendente);
        var uri = uriComponentsBuilder.path("/atendentes/{idAtendente}").buildAndExpand(atendente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseAtendenteDto(atendente));
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> cadastrarVendasSemanais(RequestVendasDto vendasDto) {
        var atendente = this.atendenteRepository.findByIdAndAtivoTrue(vendasDto.idAtendente()).orElseThrow(this::throwAtendenteNotFoundException);
        var vendas = new VendasSemanais(atendente, vendasDto);
        atendente.setVendasSemanais(vendas);
        this.vendasSemanaisRepository.save(vendas);
        this.atendenteRepository.save(atendente);
        return ResponseEntity.ok(new ResponseAtendenteDto(atendente));
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> atualizar(Long id, RequestAtendenteDto dados) {
        var atendente = this.atendenteRepository.findByIdAndAtivoTrue(id).orElseThrow(this::throwAtendenteNotFoundException);
        this.factory.atualizar(atendente, dados);
        this.atendenteRepository.save(atendente);
        return ResponseEntity.ok(new ResponseAtendenteDto(atendente));
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        var atendente = this.atendenteRepository.findById(id).orElseThrow(this::throwAtendenteNotFoundException);
        atendente.setAtivo(false);
        this.atendenteRepository.save(atendente);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> vincularAtendenteALoja(RequestVinculoLojaDto dto) {
        var atendente = this.atendenteRepository.findByIdAndAtivoTrue(dto.idAtendnete()).orElseThrow(this::throwAtendenteNotFoundException);
        var loja = this.lojaRepository.findByIdAndAtivoTrue(dto.idLoja()).orElseThrow(this::throwAtendenteNotFoundException);
        atendente.setLoja(loja);
        this.atendenteRepository.save(atendente);
        return ResponseEntity.ok(new ResponseAtendenteDto(atendente));
    }

    private ControllerNotFoundException throwAtendenteNotFoundException() {
        return new ControllerNotFoundException("Atendente não encontrado");
    }
}
