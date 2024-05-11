package br.com.erp.apierp.service;

import br.com.erp.apierp.model.Endereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    public Endereco buscaEndereco(String cep) {
        final String url = "https://viacep.com.br/ws/" + cep + "/json/";
        var restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Endereco.class);
    }
}
