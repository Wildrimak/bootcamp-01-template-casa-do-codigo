package br.com.zup.casadocodigo.api.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import br.com.zup.casadocodigo.domain.models.CupomRepository;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private CupomRepository cupomRepository;

	private final Logger logger = LoggerFactory.getLogger(Compra.class);

	@Transactional
	@PostMapping
	public ResponseEntity<?> realizarCompra(@RequestBody @Valid CompraRequest request,
			UriComponentsBuilder uriComponentsBuilder) {

		Compra compra = request.toModel(entityManager, cupomRepository);
		compra.getPedido().setCompra(compra);
		logger.info("Compra = {} criada com sucesso!", compra);

		entityManager.persist(compra);

		return ResponseEntity.created(uriComponentsBuilder.path("/compras/{id}").buildAndExpand(compra.getId()).toUri())
				.body(compra);
	}

}
