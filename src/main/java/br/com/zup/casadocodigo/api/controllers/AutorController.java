package br.com.zup.casadocodigo.api.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.casadocodigo.api.dtos.requests.AutorRequest;
import br.com.zup.casadocodigo.domain.models.Autor;

@RestController
@RequestMapping("/autores")
public class AutorController {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@PostMapping // 1
	public ResponseEntity<Autor> cadastrarAutor(@Valid @RequestBody AutorRequest autorRequest,
			UriComponentsBuilder uriComponentsBuilder) {

		// 2
		Autor autor = autorRequest.toModel();

		manager.persist(autor);

		return ResponseEntity.created(uriComponentsBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri())
				.body(autor);
	}

}
