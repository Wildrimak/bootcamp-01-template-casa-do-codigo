package br.com.zup.casadocodigo.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zup.casadocodigo.api.constraints.ExisteIdConstraintValidator;

@Constraint(validatedBy = ExisteIdConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExisteIdAnnotation {

	String message() default "{br.com.zup.casadocodigo}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?> classe();

	String atributo();

}
