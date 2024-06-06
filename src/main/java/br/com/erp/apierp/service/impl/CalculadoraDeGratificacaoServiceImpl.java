package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestCalculadoraDto;
import br.com.erp.apierp.dto.response.ResponseCalculadoraDto;
import br.com.erp.apierp.exception.ControllerNotFoundException;
import br.com.erp.apierp.factory.CalculadoraDeGratificacaoFactory;
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
    private final CalculadoraDeGratificacaoFactory factory;

    @Override
    public ResponseEntity<ResponseCalculadoraDto> cadastrar(RequestCalculadoraDto dto, UriComponentsBuilder uriComponentsBuilder) {
        var calculadora = this.factory.cadastraCalculadora(dto);
        var loja = this.lojaRepository.findByIdAndAtivoTrue(dto.idLoja()).orElseThrow(this::throwLojaNotFoundException);
        calculadora.setLoja(loja);
        var uri = uriComponentsBuilder.path("/calculadora/{id}").buildAndExpand(calculadora.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCalculadoraDto(calculadora));
    }

    @Override
    public ResponseEntity<ResponseCalculadoraDto> atualizar(RequestCalculadoraDto dto, Long idCalculadora) {
        return null;
    }

    @Override
    public ResponseEntity<Page<ResponseCalculadoraDto>> listar(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseCalculadoraDto> buscarPorId(Long idCalculadora) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletar(Long idCalculadora) {
        return null;
    }

    private ControllerNotFoundException throwLojaNotFoundException() {
        return new ControllerNotFoundException("Loja não encontrada");
    }

    private ControllerNotFoundException throwCalculadoraNOtFoundException() {
        return new ControllerNotFoundException("Calculadora não encontrada");
    }
}
