package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestAtendenteDto;
import br.com.erp.apierp.dto.request.RequestVendasDto;
import br.com.erp.apierp.dto.response.ResponseAtendenteDto;
import br.com.erp.apierp.model.Atendente;
import br.com.erp.apierp.repository.AtendenteRepository;
import br.com.erp.apierp.service.AtendenteService;
import br.com.erp.apierp.service.EnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class AtendenteServiceImpl implements AtendenteService {

    private final int PRIMEIRA_SEMANA = 0;
    private final int SEGUNDA_SEMANA = 1;
    private final int TERCEIRA_SEMANA = 2;
    private final int QUARTA_SEMANA = 3;
    private final int QUINTA_SEMANA = 4;
    private final int SEXTA_SEMANA = 5;

    @Autowired
    private AtendenteRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EnderecoService enderecoService;

    @Override
    public ResponseEntity<Page<ResponseAtendenteDto>> listarTodos(Pageable pageable) {
        var page = this.repository.findAll(pageable);
        var pageDto = page.map(entidade -> this.modelMapper.map(entidade, ResponseAtendenteDto.class));
        return ResponseEntity.ok(pageDto);
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> buscarPorId(Long id) {
        var atendente = this.repository.getReferenceById(id);
        var dto = this.modelMapper.map(atendente, ResponseAtendenteDto.class);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> cadastrar(RequestAtendenteDto dados, UriComponentsBuilder uriComponentsBuilder) {
        var atendente = this.modelMapper.map(dados, Atendente.class);
        var endereco = this.enderecoService.buscaEndereco(dados.pessoaDto().endereco().cep());
        atendente.setEndereco(endereco);
        this.atribuirVendas(atendente);
        this.repository.save(atendente);
        var uri = uriComponentsBuilder.path("/atendentes/{idAtendente}").buildAndExpand(atendente.getId()).toUri();
        return ResponseEntity.created(uri).body(this.modelMapper.map(atendente, ResponseAtendenteDto.class));
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> cadastrarVendasSemanais(RequestVendasDto vendasDto) {
        var atendente = this.repository.getReferenceById(vendasDto.idAtendente());
        this.atribuirVendas(atendente, vendasDto);
        this.repository.save(atendente);
        return ResponseEntity.ok(this.modelMapper.map(atendente, ResponseAtendenteDto.class));
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> atualizar(Long id, RequestAtendenteDto dados) {
        var atendente = this.repository.getReferenceById(id);
        this.modelMapper.map(dados, atendente);
        this.repository.save(atendente);
        return ResponseEntity.ok(this.modelMapper.map(atendente, ResponseAtendenteDto.class));
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private void atribuirVendas(Atendente atendente, RequestVendasDto vendasDto) {
        Map<Integer, Supplier<BigDecimal>> vendasMap = new HashMap<>();
        vendasMap.put(PRIMEIRA_SEMANA, vendasDto::vendasPrimeiraSemana);
        vendasMap.put(SEGUNDA_SEMANA, vendasDto::vendasSegundaSemana);
        vendasMap.put(TERCEIRA_SEMANA, vendasDto::vendasTerceiraSemana);
        vendasMap.put(QUARTA_SEMANA, vendasDto::vendasQuartaSemana);
        vendasMap.put(QUINTA_SEMANA, vendasDto::vendasQuintaSemana);
        vendasMap.put(SEXTA_SEMANA, vendasDto::vendasSextaSemana);

        vendasMap.forEach((semana, spplier) -> {
            var vendas = spplier.get();
            if (vendas != null) {
                atendente.getVendasSemanais().set(semana, spplier.get());
            }else {
                atendente.getVendasSemanais().set(semana, BigDecimal.ZERO);
            }
        });
    }

    private void atribuirVendas(Atendente atendente) {
        for (int i = 0; i < atendente.getVendasSemanais().size(); i++) {
            atendente.getVendasSemanais().set(i, BigDecimal.ZERO);
        }
    }
}
