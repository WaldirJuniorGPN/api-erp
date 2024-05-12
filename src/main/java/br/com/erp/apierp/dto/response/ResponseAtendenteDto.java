package br.com.erp.apierp.dto.response;

import br.com.erp.apierp.dto.request.RequestEnderecoDto;

import java.math.BigDecimal;
import java.util.List;

public record ResponseAtendenteDto(Long id, String nome, String cpf, String email, String telefone,
                                   RequestEnderecoDto enderecoDto, List<BigDecimal> vemdasSemanais,
                                   BigDecimal vendasTotal, BigDecimal gratificacao, BigDecimal bonus) {
}
