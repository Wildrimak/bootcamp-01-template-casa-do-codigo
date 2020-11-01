package br.com.zup.casadocodigo.domain.models;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// 1
public interface CupomRepository extends JpaRepository<Cupom, Integer> {

	public Optional<Cupom> findByCodigo(String codigo);

}
