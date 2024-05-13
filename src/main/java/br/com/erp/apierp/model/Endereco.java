package br.com.erp.apierp.model;

import br.com.erp.apierp.dto.request.RequestEnderecoDto;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class Endereco {
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String cep;
    private String ibge;
    private String ddd;
    private String siafi;

    public Endereco(RequestEnderecoDto dados){
        this.logradouro = dados.logradouro();
        this.complemento = dados.complemento();
        this.bairro = dados.bairro();
        this.localidade = dados.localidade();
        this.uf = dados.uf();
        this.ibge = dados.ibge();
        this.cep = dados.cep();
        this.ddd = dados.ddd();
        this.siafi = dados.siafi();
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                ", ibge='" + ibge + '\'' +
                ", ddd='" + ddd + '\'' +
                ", siafi='" + siafi + '\'' +
                '}';
    }
}
