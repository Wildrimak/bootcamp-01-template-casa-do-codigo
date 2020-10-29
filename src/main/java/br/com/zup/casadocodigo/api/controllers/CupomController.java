package br.com.zup.casadocodigo.api.controllers;

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

import br.com.zup.casadocodigo.api.dtos.requests.CupomRequest;
import br.com.zup.casadocodigo.domain.models.Cupom;

@RestController
@RequestMapping("cupons")
public class CupomController {

	@Autowired
	private EntityManager entityManager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criarCupom(@RequestBody @Valid CupomRequest request, UriComponentsBuilder uriComponentsBuilder){
		
		Cupom cupom = request.toModel();
		
		entityManager.persist(cupom);
		
		return ResponseEntity.created(uriComponentsBuilder.path("/compras/{id}").buildAndExpand(cupom.getId()).toUri())
				.body(cupom);
		
	}
	
}
