package eu.kalafatic.protocols.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import eu.kalafatic.protocols.annotations.validators.SizeValidator;

@Inherited
@Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = SizeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Requires {

	String name() default "";

	String[] parameters();

	String[] exceptions();

	String returns();

}
