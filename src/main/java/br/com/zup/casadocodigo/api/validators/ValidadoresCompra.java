package br.com.zup.casadocodigo.api.validators;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public interface ValidadoresCompra {

	
	public static boolean documentoValido(String documento) {

		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);

		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);

		return cpfValidator.isValid(documento, null)
				|| cnpjValidator.isValid(documento, null);
	}
	
}
