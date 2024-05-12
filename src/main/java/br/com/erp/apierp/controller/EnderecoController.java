package br.com.erp.apierp.controller;

import br.com.erp.apierp.dto.response.ResponseEnderecoDto;
import br.com.erp.apierp.service.EnderecoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{cep}")
    public ResponseEntity<ResponseEnderecoDto> buscarEndereco(@PathVariable @Valid @Pattern(regexp = "\\d{8}") String cep) {
        var endereco = this.service.buscaEndereco(cep);
        var dto = this.modelMapper.map(endereco, ResponseEnderecoDto.class);
        return ResponseEntity.ok(dto);
    }
}
