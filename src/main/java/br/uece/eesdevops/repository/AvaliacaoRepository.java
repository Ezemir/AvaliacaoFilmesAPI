package br.uece.eesdevops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uece.eesdevops.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
