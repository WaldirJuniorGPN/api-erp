package br.com.erp.apierp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    public String buscaEndereco(String cep) {
        final String url = "https://viacep.com.br/ws/" + cep.replace("-","") + "/json/";
        var restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
