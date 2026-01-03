package eu.kalafatic.protocols.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import eu.kalafatic.protocols.annotations.validators.PositiveIntConstraintValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Constraint(validatedBy = PositiveIntConstraintValidator.class)
public @interface PositiveInt {
	String message() default "positive.int.msg";

	Class<?>[] groups() default {};

	// Class<? extends Payload>[] payload() default {};

	int integer() default 1;
}
