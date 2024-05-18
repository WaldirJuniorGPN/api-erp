package br.com.erp.apierp.service;

import br.com.erp.apierp.dto.request.ConverteLojaDto;

public interface ConverteJsonService {
    ConverteLojaDto converterJson(String json);
}
