package br.com.erp.apierp.repository;

import br.com.erp.apierp.model.Atendente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {

    Optional<Page<Atendente>> findAllByAtivoTrue(Pageable pageable);
    Optional<Atendente> findByIdAndAtivoTrue(Long id);
}
