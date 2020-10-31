package br.com.zup.casadocodigo.api.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;

@Component
public class IniciarValidadoresControllerCompra {

	@Autowired // > 2
	private CpfCnpjValidator cpfCnpjValidator;

	@Autowired // > 3
	private EstadoDeveFazerParteDestePaisValidator estadoDeveFazerParteDestePaisValidator;

	public void init(WebDataBinder binder) {
		binder.addValidators(cpfCnpjValidator, estadoDeveFazerParteDestePaisValidator);

	}

}
