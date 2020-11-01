package br.com.zup.casadocodigo.api.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigo.api.dtos.requests.CompraRequest;

@Component
public class CpfCnpjValidator implements Validator {

	@Override // 1
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

		// 1
		if (!request.documentoValido()) {
			errors.rejectValue("documento", null, "documento precisa ser cpf ou cnpj");
		}

	}

}
