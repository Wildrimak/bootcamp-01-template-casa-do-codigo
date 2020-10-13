package br.com.zup.casadocodigo.api.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.casadocodigo.api.dto.requests.AutorDtoRequest;
import br.com.zup.casadocodigo.domain.models.Autor;

@RestController
@RequestMapping("/autores")
public class AutorController { // cdd : 3

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@PostMapping
	public ResponseEntity<Autor> cadastrarAutor(@Valid @RequestBody AutorDtoRequest autorDtoRequest,
			UriComponentsBuilder uriComponentsBuilder) {

		Autor autor = autorDtoRequest.toModel();

		Query query = manager.createQuery("select 1 from " + Autor.class.getName() + " where email=:value");
		query.setParameter("value", autor.getEmail());

		if (query.getResultList().size() > 0) {
			throw new IllegalArgumentException("O email não pode ser duplicado");
		}

		manager.persist(autor);

		return ResponseEntity.created(uriComponentsBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri())
				.body(autor);
	}

}
