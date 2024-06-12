package br.com.erp.apierp.model;

import br.com.erp.apierp.dto.request.RequestCalculadoraDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "calculadoras")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = true)
public class CalculadoraDeGratificacao extends EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String nome;
    private BigDecimal percentualPrimeiroColocado = BigDecimal.ZERO;
    private BigDecimal percentualSegundoColocado = BigDecimal.ZERO;
    private BigDecimal percentualTerceiroColocado = BigDecimal.ZERO;
    private BigDecimal percentualDemaisColocados = BigDecimal.ZERO;
    private BigDecimal bonusPrimeiroColocado = BigDecimal.ZERO;
    private BigDecimal bonusSegundoColocado = BigDecimal.ZERO;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    private Loja loja;

    private boolean ativo = true;

}
