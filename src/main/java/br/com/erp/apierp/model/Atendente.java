package br.com.erp.apierp.model;

import br.com.erp.apierp.dto.request.RequestAtendenteDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "atendentes")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id", callSuper = true)
public class Atendente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vendas_semanais_id")
    private VendasSemanais vendasSemanais = new VendasSemanais();
    private BigDecimal vendasTotal = BigDecimal.ZERO;
    private BigDecimal gratificacao = BigDecimal.ZERO;
    private BigDecimal bonus = BigDecimal.ZERO;

    public Atendente(RequestAtendenteDto dados) {
        super.setNome(dados.nome());
        super.setCpf(dados.cpf());
        super.setEmail(dados.email());
        super.setTelefone(dados.telefone());
    }
}
