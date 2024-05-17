package br.com.erp.apierp.service.impl;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CNPJService {

    private static final Logger LOGGER = Logger.getLogger(CNPJService.class.getName());
    private final String URL = "https://publica.cnpj.ws/cnpj/";

    public String obterDadosCnpj(String cnpj) {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + cnpj))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Ocorreu um erro ao carregar os dados do CNPJ", e);
            return null;
        }
    }
}