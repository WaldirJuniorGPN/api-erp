package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.service.EnderecoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Override
    public String buscaEndereco(String cep) {
        final String url = "https://viacep.com.br/ws/" + cep.replace("-", "") + "/json/";
        var restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
