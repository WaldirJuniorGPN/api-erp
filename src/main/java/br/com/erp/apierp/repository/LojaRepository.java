package br.com.erp.apierp.repository;

import br.com.erp.apierp.model.Loja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<Loja, Long> {

    Page<Loja> findAllByAtivoTrue(Pageable pageable);

    Loja findByIdAndAtivoTrue(Long id);
}
