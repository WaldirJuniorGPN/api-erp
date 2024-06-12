package br.com.erp.apierp.repository;

import br.com.erp.apierp.model.VendasSemanais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendasSemanaisRepository extends JpaRepository<VendasSemanais, Long> {

    Optional<VendasSemanais> findByIdAndAtivoTrue(Long id);

    Optional<Page<VendasSemanais>> findAllByAtivoTrue(Pageable pageable);
}
