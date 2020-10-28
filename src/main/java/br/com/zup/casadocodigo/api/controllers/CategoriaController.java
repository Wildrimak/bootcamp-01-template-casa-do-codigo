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

import br.com.zup.casadocodigo.api.dtos.requests.CategoriaRequest;
import br.com.zup.casadocodigo.domain.models.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaController { // cdd : 3

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@PostMapping
	public ResponseEntity<?> cadastrar(@Valid @RequestBody CategoriaRequest categoriaRequest,
			UriComponentsBuilder uriComponentsBuilder) {

		Categoria categoria = categoriaRequest.toModel();

		manager.persist(categoria);

		return ResponseEntity
				.created(uriComponentsBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri())
				.body(categoria);

	}

}
