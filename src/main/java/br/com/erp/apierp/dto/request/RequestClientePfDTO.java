package br.com.erp.apierp.dto.request;

import br.com.erp.apierp.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestClientePfDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}-\\d{2}")
        String cpf,
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{2}\\s\\d{5}-\\d{4}")
        String telefone,
        @Valid
        Endereco endereco
) {
}

