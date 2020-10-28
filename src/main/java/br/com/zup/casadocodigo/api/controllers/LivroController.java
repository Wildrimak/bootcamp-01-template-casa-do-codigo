package br.com.zup.casadocodigo.api.controllers;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.casadocodigo.api.dtos.requests.LivroRequest;
import br.com.zup.casadocodigo.domain.models.Livro;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	EntityManager entityManager;

	@Transactional
	@PostMapping
	public ResponseEntity<?> cadastrarLivro(@Valid @RequestBody LivroRequest livroRequest,
			UriComponentsBuilder uriComponentsBuilder) {

		Livro livro = livroRequest.toModel(entityManager);

		entityManager.persist(livro);

		return ResponseEntity
				.created(uriComponentsBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri())
				.body(livro);

	}

}