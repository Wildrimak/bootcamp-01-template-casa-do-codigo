package br.com.zup.casadocodigo.api.constraints;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

import br.com.zup.casadocodigo.api.annotations.ValorUnicoAnnotation;

// 1
public class ValorUnicoConstraintValidator implements ConstraintValidator<ValorUnicoAnnotation, Object> {

	private String atributo;
	private Class<?> classe;

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ValorUnicoAnnotation parametros) {
		this.atributo = parametros.atributo();
		this.classe = parametros.classe();

	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from " + classe.getName() + "" + " where " + atributo + "=:value");
		query.setParameter("value", value);

		List<?> list = query.getResultList();

		Assert.state(list.size() <= 1,
				"Em " + classe + " o campo " + atributo + " de valor " + value + " já foi cadastrado(a)");

		return list.isEmpty();
	}

}
