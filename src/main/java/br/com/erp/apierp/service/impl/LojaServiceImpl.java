package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.ConverteLojaDto;
import br.com.erp.apierp.dto.request.RequestEnderecoDto;
import br.com.erp.apierp.dto.request.RequestLoja;
import br.com.erp.apierp.dto.request.RequestLojaAutomatizado;
import br.com.erp.apierp.dto.response.ResponseLoja;
import br.com.erp.apierp.model.Endereco;
import br.com.erp.apierp.model.Loja;
import br.com.erp.apierp.repository.LojaRepository;
import br.com.erp.apierp.service.CNPJService;
import br.com.erp.apierp.service.EnderecoService;
import br.com.erp.apierp.service.IConverteDados;
import br.com.erp.apierp.service.LojaService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    private LojaRepository repository;
    @Autowired
    private CNPJService cnpjService;
    @Autowired
    private IConverteDados converteDados;
    @Autowired
    private EnderecoService enderecoService;

    @Override
    public ResponseEntity<Page<ResponseLoja>> listarTodos(Pageable pageable) {
        var page = this.repository.findAllByAtivoTrue(pageable).map(ResponseLoja::new);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<ResponseLoja> buscarPorId(Long id) {
        var loja = this.repository.findByIdAndAtivoTrue(id);
        return ResponseEntity.ok(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<ResponseLoja> cadastrar(RequestLojaAutomatizado dados, UriComponentsBuilder uriComponentsBuilder) {
        var json = this.cnpjService.obterDadosCnpj(dados.cnpj());
        var dto = this.converteJson(json);
        var jsonEndereco = this.enderecoService.buscaEndereco(dto.cep());
        var enderecoDto = this.converteDados.obterDados(jsonEndereco, RequestEnderecoDto.class);
        var loja = new Loja(dto, dados.cnpj());
        var uri = uriComponentsBuilder.path("/loja/{id}").buildAndExpand(loja.getId()).toUri();
        loja.setEndereco(new Endereco(enderecoDto));
        this.repository.save(loja);
        return ResponseEntity.created(uri).body(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<ResponseLoja> cadastrar(RequestLoja dados, UriComponentsBuilder uriComponentsBuilder) {
        var loja = new Loja(dados);
        var uri = uriComponentsBuilder.path("/loja/{id}").buildAndExpand(loja.getId()).toUri();
        var jsonEndereco = this.enderecoService.buscaEndereco(dados.enderecoDto().cep());
        var enderecoDto = this.converteDados.obterDados(jsonEndereco, RequestEnderecoDto.class);
        loja.setEndereco(new Endereco(enderecoDto));
        this.repository.save(loja);
        return ResponseEntity.created(uri).body(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<ResponseLoja> atualizar(Long id, RequestLoja dados) {
        var loja = this.repository.findByIdAndAtivoTrue(id);
        this.atualizarDados(loja, dados);
        this.repository.save(loja);
        return ResponseEntity.ok(new ResponseLoja(loja));
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        var loja = this.repository.getReferenceById(id);
        loja.setAtivo(false);
        this.repository.save(loja);
        return ResponseEntity.noContent().build();
    }

    private void atualizarDados(Loja loja, RequestLoja dados) {
        loja.setCnpj(dados.cnpj());
        loja.setRazaoSocial(dados.razaoSocial());
        loja.setNomeFantasia(dados.nomeFantasia());
        loja.setInscricaoEstadual(dados.inscricaoEstadual());
        loja.setTelefone(dados.telefone());
        loja.setEmail(dados.email());
        loja.setEndereco(new Endereco(dados.enderecoDto()));
    }

    private ConverteLojaDto converteJson(String json) {

        var mapper = new ObjectMapper();
        ConverteLojaDto dto = null;

        try {
            JsonNode rootNode = mapper.readTree(json);

            String razaoSocial = rootNode.path("razao_social").asText();
            String nomeFantasia = rootNode.path("estabelecimento").path("nome_fantasia").asText();
            String inscricaoEstadual = rootNode.path("estabelecimento").path("inscricoes_estaduais").get(0).path("inscricao_estadual").asText();
            String telefone = rootNode.path("estabelecimento").path("ddd1").asText() + rootNode.path("estabelecimento").path("telefone1").asText();
            String email = rootNode.path("estabelecimento").path("email").asText();
            String cep = rootNode.path("estabelecimento").path("cep").asText();
            String complemento = rootNode.path("estabelecimento").path("complemento").asText();

            dto = new ConverteLojaDto(
                    razaoSocial,
                    nomeFantasia,
                    inscricaoEstadual,
                    telefone,
                    email,
                    cep,
                    complemento
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}
