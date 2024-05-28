package br.com.erp.apierp.repository;

import br.com.erp.apierp.model.Loja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LojaRepository extends JpaRepository<Loja, Long> {

    Optional<Page<Loja>> findAllByAtivoTrue(Pageable pageable);

    Optional<Loja> findByIdAndAtivoTrue(Long id);
}
