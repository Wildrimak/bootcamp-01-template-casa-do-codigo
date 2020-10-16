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

import br.com.zup.casadocodigo.api.dto.requests.LivroDtoRequest;
import br.com.zup.casadocodigo.domain.models.Livro;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	EntityManager entityManager;

	@Transactional
	@PostMapping
	public ResponseEntity<?> cadastrarLivro(@Valid @RequestBody LivroDtoRequest livroDtoRequest,
			UriComponentsBuilder uriComponentsBuilder) {

		Livro livro = livroDtoRequest.toModel(entityManager);

		entityManager.persist(livro);

		return ResponseEntity
				.created(uriComponentsBuilder.path("/categorias/{id}").buildAndExpand(livro.getId()).toUri())
				.body(livro);

	}

}