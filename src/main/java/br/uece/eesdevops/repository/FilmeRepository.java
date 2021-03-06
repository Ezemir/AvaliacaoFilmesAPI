package br.uece.eesdevops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uece.eesdevops.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{
}
