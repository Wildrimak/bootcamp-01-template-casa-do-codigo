package br.com.zup.casadocodigo.api.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigo.api.dtos.requests.CompraRequest;
import br.com.zup.casadocodigo.domain.models.Estado;
import br.com.zup.casadocodigo.domain.models.Pais;

@Component
public class EstadoDeveFazerParteDestePaisValidator implements Validator {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean supports(Class<?> clazz) {
		return CompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		// 1
		if (errors.hasErrors()) {
			return;
		}

		CompraRequest request = (CompraRequest) target;

		// 2
		if (request.temEstado()) {

			// 3
			Pais pais = entityManager.find(Pais.class, request.getIdPais());

			// 4
			Estado estado = entityManager.find(Estado.class, request.getIdEstado());

			// 5
			if (!estado.pertenceAPais(pais)) {
				errors.rejectValue("idEstado", null, "Este estado não é o do país selecionado");
			}

		}

	}

}
