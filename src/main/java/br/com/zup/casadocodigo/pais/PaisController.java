package br.com.zup.casadocodigo.pais;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.estado.EstadoRequesDto;

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

	@Transactional
	@PostMapping("{idPais}/estados")
	public ResponseEntity<?> cadastrarEstado(@Valid @RequestBody EstadoRequesDto request, @PathVariable Integer idPais,
			UriComponentsBuilder uriComponentsBuilder) {

		Estado estado = request.toModel(manager);
		System.out.println(estado.getNome());
		System.out.println(estado.getPais());
		System.out.println(estado.getPais().getId());
		System.out.println(estado.getPais().getNome());
		Pais pais = manager.find(Pais.class, idPais);

		Estado novo = new Estado();
		novo.setNome(estado.getNome());
		novo.setPais(pais);
		novo.setAlfa("alfa");
		novo.setTheta(new Date());
	
		manager.persist(novo);

		return ResponseEntity
				.created(uriComponentsBuilder.path("/paises/{id}/estados/{id}").buildAndExpand(estado.getIdEstado()).toUri())
				.body(estado);
	}

}
