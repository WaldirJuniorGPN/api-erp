package br.com.erp.apierp.model;

import br.com.erp.apierp.dto.request.RequestClientePfDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id", callSuper = true)
public class ClientePF extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ClientePF(RequestClientePfDTO dados) {
        super.setNome(dados.pessoaDto().nome());
        super.setCpf(dados.pessoaDto().cpf());
        super.setEmail(dados.pessoaDto().email());
        super.setTelefone(dados.pessoaDto().telefone());
    }
}
