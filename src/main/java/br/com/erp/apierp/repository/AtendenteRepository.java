package br.com.erp.apierp.repository;

import br.com.erp.apierp.model.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
}
