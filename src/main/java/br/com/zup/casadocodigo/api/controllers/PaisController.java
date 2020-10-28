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

import br.com.zup.casadocodigo.api.dtos.PaisRequestDto;
import br.com.zup.casadocodigo.domain.models.Pais;

@RestController
@RequestMapping("/paises")
public class PaisController {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@PostMapping
	public ResponseEntity<?> cadastrarPais(@Valid @RequestBody PaisRequestDto request,
			UriComponentsBuilder uriComponentsBuilder) {

		Pais pais = new Pais(request.getNome());

		manager.persist(pais);

		return ResponseEntity.created(uriComponentsBuilder.path("/paises/{id}").buildAndExpand(pais.getId()).toUri())
				.body(pais);
	}

}
