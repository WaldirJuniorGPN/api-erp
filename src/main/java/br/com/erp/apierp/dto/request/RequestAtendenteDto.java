package br.com.erp.apierp.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestAtendenteDto(
        @NotBlank(message = "O campo 'nome' não pode estar em branco")
        String nome,
        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}-\\d{2}")
        String cpf,
        @Email(message = "Informe um e-mail válido")
        String email,
        @Pattern(regexp = "\\d{2}\\s\\d{5}-\\d{4}")
        String telefone,
        @Valid
        RequestEnderecoDto endereco
) {
}
