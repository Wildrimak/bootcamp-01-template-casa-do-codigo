package br.com.zup.casadocodigo.api.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.casadocodigo.api.dtos.requests.CompraRequest;
import br.com.zup.casadocodigo.api.dtos.responses.CompraResponse;
import br.com.zup.casadocodigo.api.validators.IniciarValidadoresControllerCompra;
import br.com.zup.casadocodigo.domain.models.Compra;
import br.com.zup.casadocodigo.domain.models.CupomRepository;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired // > 1
	private CupomRepository cupomRepository;
	
	@Autowired // > 2
	private IniciarValidadoresControllerCompra iniciarValidadoresControllerCompra;
		
	@InitBinder
	public void init(WebDataBinder binder) {
		iniciarValidadoresControllerCompra.init(binder);
	}

	@Transactional
	@PostMapping // > 3
	public ResponseEntity<?> realizarCompra(@RequestBody @Valid CompraRequest request,
			UriComponentsBuilder uriComponentsBuilder) {

		// > 4
		Compra compra = request.toModel(entityManager, cupomRepository);
		compra.getPedido().setCompra(compra);
		
		// 5
		compra.getPedido().getItensPedidos()
			.forEach(item -> item.setPedido(compra.getPedido()));

		entityManager.persist(compra);

		return ResponseEntity.created(uriComponentsBuilder.path("/compras/{id}").buildAndExpand(compra.getId()).toUri())
				.body(compra);
	}
	
	@GetMapping("/{id}") // > 6
	public ResponseEntity<CompraResponse> detalheCompra(@PathVariable Integer id) {

		Compra compra = entityManager.find(Compra.class, id);

		// > 7
		if (compra == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new CompraResponse(compra));
	}

}
