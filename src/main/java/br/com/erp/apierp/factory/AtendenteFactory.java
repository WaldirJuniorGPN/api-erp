package br.com.erp.apierp.factory;

import br.com.erp.apierp.dto.request.RequestAtendenteDto;
import br.com.erp.apierp.model.Atendente;

public interface AtendenteFactory {

    Atendente criaAtendente(RequestAtendenteDto dados);

    void atualizaAtendente(Atendente atendente, RequestAtendenteDto dto);
}
