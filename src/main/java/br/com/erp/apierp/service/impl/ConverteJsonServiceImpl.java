package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.dto.request.ConverteLojaDto;
import br.com.erp.apierp.service.ConverteJsonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConverteJsonServiceImpl implements ConverteJsonService {

    @Override
    public ConverteLojaDto converterJson(String json) {
        var mapper = new ObjectMapper();
        ConverteLojaDto dto = null;

        try {
            JsonNode rootNode = mapper.readTree(json);

            String razaoSocial = rootNode.path("razao_social").asText();
            String nomeFantasia = rootNode.path("estabelecimento").path("nome_fantasia").asText();
            String inscricaoEstadual = rootNode.path("estabelecimento").path("inscricoes_estaduais").get(0).path("inscricao_estadual").asText();
            String telefone = rootNode.path("estabelecimento").path("ddd1").asText() + rootNode.path("estabelecimento").path("telefone1").asText();
            String email = rootNode.path("estabelecimento").path("email").asText();
            String cep = rootNode.path("estabelecimento").path("cep").asText();
            String complemento = rootNode.path("estabelecimento").path("complemento").asText();

            dto = new ConverteLojaDto(
                    razaoSocial,
                    nomeFantasia,
                    inscricaoEstadual,
                    telefone,
                    email,
                    cep,
                    complemento
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}
