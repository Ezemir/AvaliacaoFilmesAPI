package br.uece.eesdevops.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.model.Avaliacao;
import br.uece.eesdevops.repository.AvaliacaoRepository;

@RestController
@RequestMapping({ "/avaliacao" })
public class AvaliacaoController {

	private AvaliacaoRepository avaliacaoRepository;

	public AvaliacaoController(AvaliacaoRepository avaliacaoRespository) {
		this.avaliacaoRepository = avaliacaoRespository;
	}

	@GetMapping
	public List findAll() {
		return avaliacaoRepository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity findById(@PathVariable Long id) {
		return avaliacaoRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Avaliacao create(@RequestBody Avaliacao avaliacao) {
		return avaliacaoRepository.save(avaliacao);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Avaliacao> update(@PathVariable("id") Long id, @RequestBody Avaliacao avaliacao) {
		return avaliacaoRepository.findById(id).map(record -> {
			record.setNomeAvaliador(avaliacao.getNomeAvaliador());
			record.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
			record.setComentario(avaliacao.getComentario());
			record.setFilme(avaliacao.getFilme());

			Avaliacao updated = avaliacaoRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return avaliacaoRepository.findById(id).map(record -> {
			avaliacaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
