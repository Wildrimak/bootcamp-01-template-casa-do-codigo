package br.com.zup.casadocodigo.realizarcompra;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/compras")
public class CompraController {

	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> realizarCompra(@RequestBody @Valid CompraRequestDto request, UriComponentsBuilder uriComponentsBuilder){
		
		Compra compra = request.toModel(entityManager);		

		entityManager.persist(compra);

		return ResponseEntity.created(uriComponentsBuilder.path("/compras/{id}").buildAndExpand(compra.getId()).toUri())
				.body(compra);
	}
	
}
