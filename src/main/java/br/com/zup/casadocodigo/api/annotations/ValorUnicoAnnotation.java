package br.com.zup.casadocodigo.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zup.casadocodigo.api.constraints.ValorUnicoConstraintValidator;

@Constraint(validatedBy = ValorUnicoConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValorUnicoAnnotation {

	String message() default "Este valor já foi cadastrado, tente um valor diferente";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?> classe();

	String atributo();

}
