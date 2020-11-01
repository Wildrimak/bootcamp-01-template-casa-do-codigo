package br.com.zup.casadocodigo.api.constraints;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

import br.com.zup.casadocodigo.api.annotations.ExisteIdAnnotation;

public class ExisteIdConstraintValidator implements ConstraintValidator<ExisteIdAnnotation, Object> {

	private String atributo;
	private Class<?> classe;

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ExisteIdAnnotation parametros) {
		this.atributo = parametros.atributo();
		this.classe = parametros.classe();

	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		// Não é aqui que validamos a obrigatoriedade do Id;
		if(value == null) {
			return true;
		}
		
		Query query = manager.createQuery("select 1 from " + classe.getName() + "" + " where " + atributo + "=:value");
		query.setParameter("value", value);

		List<?> list = query.getResultList();

		Assert.state(list.size() == 1,
				"Esse valor em " + classe.getSimpleName() + " no campo " + atributo + ": " + value + " não existe!");

		return !list.isEmpty();
	}
}