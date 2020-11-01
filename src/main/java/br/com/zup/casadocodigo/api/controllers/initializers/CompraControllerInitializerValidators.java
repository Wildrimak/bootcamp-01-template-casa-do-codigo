package br.com.zup.casadocodigo.api.controllers.initializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;

import br.com.zup.casadocodigo.api.validators.CpfCnpjValidator;
import br.com.zup.casadocodigo.api.validators.EstadoDeveFazerParteDestePaisValidator;
import br.com.zup.casadocodigo.api.validators.ValorRecebidoIgualValorCalculadoValidator;

@Component
public class CompraControllerInitializerValidators {

	@Autowired // > 2
	private CpfCnpjValidator cpfCnpjValidator;

	@Autowired // > 3
	private EstadoDeveFazerParteDestePaisValidator estadoDeveFazerParteDestePaisValidator;

	@Autowired
	private ValorRecebidoIgualValorCalculadoValidator valorRecebidoIgualValorCalculadoValidator;

	public void init(WebDataBinder binder) {
	
		binder.addValidators(cpfCnpjValidator, estadoDeveFazerParteDestePaisValidator,
				valorRecebidoIgualValorCalculadoValidator);

	}

}
