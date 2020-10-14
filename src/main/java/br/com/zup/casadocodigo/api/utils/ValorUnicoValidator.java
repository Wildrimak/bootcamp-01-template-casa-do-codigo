package br.com.zup.casadocodigo.api.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, String> {

	@Autowired
	private EntityManager manager;

	private String atributo;
	private Class<?> classe;

	@Override
	public void initialize(ValorUnico parametros) {

		this.atributo = parametros.atributo();
		this.classe = parametros.classe();

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Query query = manager.createQuery("select 1 from " + classe.getName() + "" + " where " + atributo + "=:value");

		query.setParameter("value", value);
		List<?> list = query.getResultList();

		return list.size() > 0;
	}

}
