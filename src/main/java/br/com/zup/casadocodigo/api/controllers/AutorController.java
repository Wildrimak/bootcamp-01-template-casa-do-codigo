package br.com.zup.casadocodigo.api.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigo.api.dto.requests.AutorDtoRequest;
import br.com.zup.casadocodigo.domain.models.Autor;

@RestController
@RequestMapping("/autores")
public class AutorController {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@PostMapping
	public ResponseEntity<Autor> cadastrarAutor(@Valid @RequestBody AutorDtoRequest autorDtoRequest) {

		Autor autor = autorDtoRequest.toModel();

		manager.persist(autor);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(autor);
	}

}
