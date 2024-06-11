package br.com.erp.apierp.repository;

import br.com.erp.apierp.model.CalculadoraDeGratificacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalculadoraDeGratificacaoRepository extends JpaRepository<CalculadoraDeGratificacao, Long> {

    Optional<Page<CalculadoraDeGratificacao>> findAllByAtivoTrue(Pageable pageable);

    Optional<CalculadoraDeGratificacao> findByIdAndAtivoTrue(Long id);
}
