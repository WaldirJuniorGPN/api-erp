package br.com.erp.apierp.factory;

import br.com.erp.apierp.dto.request.RequestClientePfDTO;
import br.com.erp.apierp.model.ClientePF;

public interface ClientePfFactory {

    ClientePF criaCliente(RequestClientePfDTO dados);

    void atualizaCliente(ClientePF cliente, RequestClientePfDTO dto);
}
