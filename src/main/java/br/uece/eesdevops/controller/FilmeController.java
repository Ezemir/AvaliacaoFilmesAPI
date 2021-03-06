package br.uece.eesdevops.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.model.Filme;
import br.uece.eesdevops.repository.FilmeRepository;

@RestController
@RequestMapping({ "/filmes" })
public class FilmeController {

	private FilmeRepository filmeRepository;

	public FilmeController(FilmeRepository filmeRepository) {
		this.filmeRepository = filmeRepository;
	}

	@GetMapping
	public List findAll() {
		return filmeRepository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity findById(@PathVariable Long id) {
		return filmeRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Filme create(@RequestBody Filme filme) {
		return filmeRepository.save(filme);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Filme> update(@PathVariable("id") Long id, @RequestBody Filme filme) {
		return filmeRepository.findById(id).map(record -> {
			record.setTitulo(filme.getTitulo());
			record.setNomeProdutor(filme.getNomeProdutor());
			record.setNomeAtor(filme.getNomeAtor());
			record.setAnoLancamento(filme.getAnoLancamento());
			Filme updated = filmeRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	   return filmeRepository.findById(id)
	           .map(record -> {
	               filmeRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}

}
