package br.com.erp.apierp.repository;

import br.com.erp.apierp.model.ClientePF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientePfRepository extends JpaRepository<ClientePF, Long> {
    Optional<Page<ClientePF>> findAllByAtivoTrue(Pageable pageable);
    Optional<ClientePF> findByIdAndAtivoTrue(Long id);
}
