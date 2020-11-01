package br.com.zup.casadocodigo.api.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigo.api.dtos.requests.CompraRequest;
import br.com.zup.casadocodigo.domain.models.CupomRepository;

@Component // cdd: 4
public class ValorRecebidoIgualValorCalculadoValidator implements Validator {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired // 1
	private CupomRepository cupomRepository;

	@Override 
	public boolean supports(Class<?> clazz) { // 1
		return CompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		// 1
		if (errors.hasErrors()) {
			return;
		}

		CompraRequest request = (CompraRequest) target;

		boolean valido = request.valorEnviadoValido(entityManager, cupomRepository);

		// 1
		if (!valido) {
			errors.rejectValue("total", null, "Este valor não bate com o que foi calculado no servidor!");
		}

	}

}
