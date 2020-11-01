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

import br.com.zup.casadocodigo.api.dtos.requests.EstadoRequest;
import br.com.zup.casadocodigo.domain.models.Estado;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@PostMapping // 1
	public ResponseEntity<?> cadastrarEstado(@Valid @RequestBody EstadoRequest request,
			UriComponentsBuilder uriComponentsBuilder) {

		// 2
		Estado estado = request.toModel(manager);

		manager.persist(estado);

		return ResponseEntity.created(uriComponentsBuilder.path("/estados/{id}").buildAndExpand(estado.getId()).toUri())
				.body(estado);
	}

}
