package br.com.zup.casadocodigo.api.controllers.initializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;

import br.com.zup.casadocodigo.api.validators.CpfCnpjValidator;
import br.com.zup.casadocodigo.api.validators.CupomValidoValidator;
import br.com.zup.casadocodigo.api.validators.EstadoDeveFazerParteDestePaisValidator;
import br.com.zup.casadocodigo.api.validators.ValorRecebidoIgualValorCalculadoValidator;

@Component
public class CompraControllerInitializerValidators {

	@Autowired // > 1
	private CpfCnpjValidator cpfCnpjValidator;

	@Autowired // > 2
	private EstadoDeveFazerParteDestePaisValidator estadoDeveFazerParteDestePaisValidator;

	@Autowired // 3
	private ValorRecebidoIgualValorCalculadoValidator valorRecebidoIgualValorCalculadoValidator;

	@Autowired // 4
	private CupomValidoValidator cupomValidoValidator;

	public void init(WebDataBinder binder) {

		binder.addValidators(cpfCnpjValidator, estadoDeveFazerParteDestePaisValidator,
				valorRecebidoIgualValorCalculadoValidator, cupomValidoValidator);

	}

}
