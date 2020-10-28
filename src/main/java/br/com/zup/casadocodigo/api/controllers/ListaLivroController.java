package br.com.zup.casadocodigo.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigo.api.dtos.responses.LivroListadoResponse;
import br.com.zup.casadocodigo.domain.models.Livro;

@RestController
@RequestMapping("/livros")
public class ListaLivroController {

	@Autowired
	EntityManager entityManager;

	@GetMapping
	public ResponseEntity<List<LivroListadoResponse>> listar() {

		List<?> livros = entityManager.createQuery("select l from Livro l").getResultList();

		List<LivroListadoResponse> livrosDtoResponse = new ArrayList<>();

		livros.forEach(livro -> {

			LivroListadoResponse livroDtoResponse = new LivroListadoResponse((Livro) livro);
			livrosDtoResponse.add(livroDtoResponse);

		});

		return ResponseEntity.ok(livrosDtoResponse);
	}

}