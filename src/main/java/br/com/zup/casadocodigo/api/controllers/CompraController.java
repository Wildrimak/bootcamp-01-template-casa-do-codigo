package br.com.zup.casadocodigo.api.controllers;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.casadocodigo.api.dtos.requests.CompraRequest;
import br.com.zup.casadocodigo.domain.models.Compra;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@Autowired
	private EntityManager entityManager;

	private final Logger logger = LoggerFactory.getLogger(Compra.class);

	@Transactional
	@PostMapping
	public ResponseEntity<?> realizarCompra(@RequestBody @Valid CompraRequest request,
			UriComponentsBuilder uriComponentsBuilder) {

		Compra compra = request.toModel(entityManager);

		logger.info("Compra = {} criada com sucesso!", compra);

		entityManager.persist(compra);

		return ResponseEntity.created(uriComponentsBuilder.path("/compras/{id}").buildAndExpand(compra.getId()).toUri())
				.body(compra);
	}

}
