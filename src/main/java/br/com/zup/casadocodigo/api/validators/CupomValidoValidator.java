package br.com.zup.casadocodigo.api.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigo.api.dtos.requests.CompraRequest;
import br.com.zup.casadocodigo.domain.models.Cupom;
import br.com.zup.casadocodigo.domain.models.CupomRepository;

@Component
public class CupomValidoValidator implements Validator {

	@Autowired
	private CupomRepository cupomRepository;

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

		// 2
		CompraRequest request = (CompraRequest) target;

		Optional<String> possivelCodigoCupom = request.getCodigoCupom();

		// 4
		if (possivelCodigoCupom.isPresent()) {

			Optional<Cupom> optionalCupom = cupomRepository.findByCodigo(possivelCodigoCupom.get());

			// 5
			if (optionalCupom.isEmpty()) {
				return;
			}

			// 6
			Cupom cupom = optionalCupom.get();

			// 7
			if (!cupom.estaValido()) {
				errors.rejectValue("codigoCupom", null, "Este cupom não é mais válido");
			}
		}
	}

}
