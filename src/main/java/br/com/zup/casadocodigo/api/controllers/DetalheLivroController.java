package br.com.zup.casadocodigo.api.controllers;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigo.api.dtos.responses.LivroDetalheResponse;
import br.com.zup.casadocodigo.domain.models.Livro;

@RestController
@RequestMapping("/livros")
public class DetalheLivroController {

	@Autowired
	private EntityManager entityManager;

	@GetMapping("/{id}")
	public ResponseEntity<LivroDetalheResponse> detalheLivro(@PathVariable Integer id) {

		Livro livro = entityManager.find(Livro.class, id);

		if (livro == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new LivroDetalheResponse(livro));
	}

}
