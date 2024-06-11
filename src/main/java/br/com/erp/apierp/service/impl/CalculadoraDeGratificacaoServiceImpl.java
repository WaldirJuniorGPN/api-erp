package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestCalculadoraDto;
import br.com.erp.apierp.dto.response.ResponseCalculadoraDto;
import br.com.erp.apierp.exception.ControllerNotFoundException;
import br.com.erp.apierp.exception.ControllerPropertyReferenceException;
import br.com.erp.apierp.factory.EntityFactory;
import br.com.erp.apierp.model.CalculadoraDeGratificacao;
import br.com.erp.apierp.repository.CalculadoraDeGratificacaoRepository;
import br.com.erp.apierp.repository.CalculadoraDeGratificacaoService;
import br.com.erp.apierp.repository.LojaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class CalculadoraDeGratificacaoServiceImpl implements CalculadoraDeGratificacaoService {

    private final CalculadoraDeGratificacaoRepository calculadoraRepository;
    private final LojaRepository lojaRepository;
    private final EntityFactory<CalculadoraDeGratificacao, RequestCalculadoraDto> factory;

    @Override
    public ResponseEntity<ResponseCalculadoraDto> cadastrar(RequestCalculadoraDto dto, UriComponentsBuilder uriComponentsBuilder) {
        var calculadora = this.factory.criar(dto);
        var loja = this.lojaRepository.findByIdAndAtivoTrue(dto.idLoja()).orElseThrow(this::throwLojaNotFoundException);
        calculadora.setLoja(loja);
        var uri = uriComponentsBuilder.path("/calculadora/{id}").buildAndExpand(calculadora.getId()).toUri();
        this.calculadoraRepository.save(calculadora);
        return ResponseEntity.created(uri).body(new ResponseCalculadoraDto(calculadora));
    }

    @Override
    public ResponseEntity<ResponseCalculadoraDto> atualizar(RequestCalculadoraDto dto, Long idCalculadora) {
        var calculadora = buscarNoBanco(idCalculadora);
        this.factory.atualizar(calculadora, dto);
        this.calculadoraRepository.save(calculadora);
        return ResponseEntity.ok(new ResponseCalculadoraDto(calculadora));
    }

    @Override
    public ResponseEntity<Page<ResponseCalculadoraDto>> listar(Pageable pageable) {
        var page = this.calculadoraRepository.findAllByAtivoTrue(pageable).orElseThrow(
                () -> new ControllerPropertyReferenceException("Parâmetros do Json estão inválidos")).map(ResponseCalculadoraDto::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<ResponseCalculadoraDto> buscarPorId(Long idCalculadora) {
        var calculadora = this.buscarNoBanco(idCalculadora);
        return ResponseEntity.ok(new ResponseCalculadoraDto(calculadora));
    }

    @Override
    public ResponseEntity<Void> deletar(Long idCalculadora) {
        var calculadora = this.buscarNoBanco(idCalculadora);
        calculadora.setAtivo(false);
        return ResponseEntity.noContent().build();
    }

    private CalculadoraDeGratificacao buscarNoBanco(Long id) {
        return this.calculadoraRepository.findByIdAndAtivoTrue(id).orElseThrow(this::throwCalculadoraNOtFoundException);
    }

    private ControllerNotFoundException throwLojaNotFoundException() {
        return new ControllerNotFoundException("Loja não encontrada");
    }

    private ControllerNotFoundException throwCalculadoraNOtFoundException() {
        return new ControllerNotFoundException("Calculadora não encontrada");
    }
}
