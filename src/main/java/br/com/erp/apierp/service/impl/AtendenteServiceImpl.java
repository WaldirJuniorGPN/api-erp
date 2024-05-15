package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.RequestAtendenteDto;
import br.com.erp.apierp.dto.request.RequestEnderecoDto;
import br.com.erp.apierp.dto.request.RequestVendasDto;
import br.com.erp.apierp.dto.response.ResponseAtendenteDto;
import br.com.erp.apierp.model.Atendente;
import br.com.erp.apierp.model.Endereco;
import br.com.erp.apierp.model.VendasSemanais;
import br.com.erp.apierp.repository.AtendenteRepository;
import br.com.erp.apierp.repository.VendasSemanaisRepository;
import br.com.erp.apierp.service.AtendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AtendenteServiceImpl implements AtendenteService {

    @Autowired
    private AtendenteRepository atendenteRepository;
    @Autowired
    private VendasSemanaisRepository vendasSemanaisRepository;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private ConverteDadosImpl converteDados;

    @Override
    public ResponseEntity<Page<ResponseAtendenteDto>> listarTodos(Pageable pageable) {
        var page = this.atendenteRepository.findAllByAtivoTrue(pageable).map(ResponseAtendenteDto::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> buscarPorId(Long id) {
        var atendente = this.atendenteRepository.findByIdAndAtivoTrue(id);
        var dto = new ResponseAtendenteDto(atendente);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> cadastrar(@Valid RequestAtendenteDto dados, UriComponentsBuilder uriComponentsBuilder) {
        var atendente = new Atendente(dados);
        var json = this.enderecoService.buscaEndereco(dados.endereco().cep());
        var endereco = new Endereco(this.converteDados.obterDados(json, RequestEnderecoDto.class));
        atendente.setEndereco(endereco);
        this.atendenteRepository.save(atendente);
        var uri = uriComponentsBuilder.path("/atendentes/{idAtendente}").buildAndExpand(atendente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseAtendenteDto(atendente));
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> cadastrarVendasSemanais(RequestVendasDto vendasDto) {
        var atendente = this.atendenteRepository.findByIdAndAtivoTrue(vendasDto.idAtendente());
        var vendas = new VendasSemanais(atendente, vendasDto);
        atendente.setVendasSemanais(vendas);
        this.vendasSemanaisRepository.save(vendas);
        this.atendenteRepository.save(atendente);
        return ResponseEntity.ok(new ResponseAtendenteDto(atendente));
    }

    @Override
    public ResponseEntity<ResponseAtendenteDto> atualizar(Long id, RequestAtendenteDto dados) {
        var atendente = this.atendenteRepository.findByIdAndAtivoTrue(id);
        this.atualizarDados(atendente, dados);
        this.atendenteRepository.save(atendente);
        return ResponseEntity.ok(new ResponseAtendenteDto(atendente));
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        var atendente = this.atendenteRepository.getReferenceById(id);
        atendente.setAtivo(false);
        this.atendenteRepository.save(atendente);
        return ResponseEntity.noContent().build();
    }

    private void atualizarDados(Atendente atendente, RequestAtendenteDto dto) {
        atendente.setNome(dto.nome());
        var json = this.enderecoService.buscaEndereco(dto.endereco().cep());
        var endereco = new Endereco(this.converteDados.obterDados(json, RequestEnderecoDto.class));
        atendente.setEndereco(endereco);
        atendente.setCpf(dto.cpf());
        atendente.setEmail(dto.email());
        atendente.setTelefone(dto.telefone());
    }
}
