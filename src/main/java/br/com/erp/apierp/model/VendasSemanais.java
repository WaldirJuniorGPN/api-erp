package br.com.erp.apierp.model;

import br.com.erp.apierp.dto.request.RequestVendasDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "vendas_semanais")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class VendasSemanais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal primeiraSemana = BigDecimal.ZERO;
    private BigDecimal segundaSemana = BigDecimal.ZERO;
    private BigDecimal terceiraSemana = BigDecimal.ZERO;
    private BigDecimal quartaSemana = BigDecimal.ZERO;
    private BigDecimal quintaSemana = BigDecimal.ZERO;
    private BigDecimal sextaSemana = BigDecimal.ZERO;
    @OneToOne(mappedBy = "vendasSemanais", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Atendente atendente;

    public VendasSemanais(Atendente atendente, RequestVendasDto vendasDto) {
        this.atendente = atendente;
        this.primeiraSemana = vendasDto.vendasPrimeiraSemana();
        this.segundaSemana = vendasDto.vendasSegundaSemana();
        this.terceiraSemana = vendasDto.vendasTerceiraSemana();
        this.quartaSemana = vendasDto.vendasQuartaSemana();
        this.quintaSemana = vendasDto.vendasQuintaSemana();
        this.sextaSemana = vendasDto.vendasSextaSemana();

        this.atendente.setVendasTotal(this.primeiraSemana, this.segundaSemana, this.terceiraSemana, this.quartaSemana, this.quintaSemana, this.sextaSemana);
    }
}
