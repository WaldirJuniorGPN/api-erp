package br.com.erp.apierp.dto.response;

import br.com.erp.apierp.model.Atendente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseAtendenteDto(Long id, String nome, String cpf, String email, String telefone,
                                   BigDecimal vendasTotal, BigDecimal gratificacao, BigDecimal bonus,
                                   ResponseEnderecoDto endereco) {
    public ResponseAtendenteDto(Atendente atendente) {
        this(atendente.getId(), atendente.getNome(), atendente.getCpf(), atendente.getEmail(), atendente.getTelefone(), atendente.getVendasTotal(), atendente.getGratificacao(), atendente.getBonus(), new ResponseEnderecoDto(atendente.getEndereco()));
    }
}
