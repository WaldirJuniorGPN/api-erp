package br.com.erp.apierp.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lojas")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id", callSuper = true)
public class Loja extends PessoaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Atendente> atendentes = new ArrayList<>();
    private BigDecimal vendaTotal = BigDecimal.ZERO;
}
