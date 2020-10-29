package br.com.zup.casadocodigo.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.casadocodigo.api.dtos.requests.AtualizaCupomRequest;
import br.com.zup.casadocodigo.api.dtos.requests.CupomRequest;
import br.com.zup.casadocodigo.domain.models.Cupom;
import br.com.zup.casadocodigo.domain.models.CupomRepository;

@RestController
@RequestMapping("cupons")
public class CupomController {

	@Autowired
	private CupomRepository cupomRepository;

	@PostMapping
	public ResponseEntity<?> criarCupom(@RequestBody @Valid CupomRequest request,
			UriComponentsBuilder uriComponentsBuilder) {

		Cupom cupom = request.toModel();

		cupomRepository.save(cupom);

		return ResponseEntity.created(uriComponentsBuilder.path("/compras/{id}").buildAndExpand(cupom.getId()).toUri())
				.body(cupom);

	}

	@PutMapping("/{idCupom}")
	public ResponseEntity<?> atualizaCupom(@RequestBody @Valid AtualizaCupomRequest request,
			@PathVariable Integer idCupom) {

		Optional<Cupom> optional = cupomRepository.findById(idCupom);

		if (optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cupom cupom = optional.get();
		Cupom cupomAtualizado = request.toModel();

		cupom.atualizarCupom(cupomAtualizado);

		cupomRepository.save(cupom);

		return ResponseEntity.noContent().build();

	}

}
