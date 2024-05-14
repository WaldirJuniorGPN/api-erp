package br.com.erp.apierp.dto.response;

import br.com.erp.apierp.model.Atendente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseAtendenteDto(Long id, String nome, String cpf, String email, String telefone,
                                   List<BigDecimal> vemdasSemanais,
                                   BigDecimal vendasTotal, BigDecimal gratificacao, BigDecimal bonus,
                                   ResponseEnderecoDto endereco) {
    public ResponseAtendenteDto(Atendente atendente) {
        this(atendente.getId(), atendente.getNome(), atendente.getCpf(), atendente.getEmail(), atendente.getTelefone(), atendente.getVendasSemanais(), atendente.getVendasTotal(), atendente.getGratificacao(), atendente.getBonus(), new ResponseEnderecoDto(atendente.getEndereco()));
    }
}
