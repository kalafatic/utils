package eu.kalafatic.protocols.annotations.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import eu.kalafatic.protocols.annotations.Requires;

public class SizeValidator implements ConstraintValidator<Requires, Integer> {

	@Override
	public void initialize(Requires constraint) {

	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext ctx) {
		if (value == null) {
			return false;
		}
		return value % 2 == 1;

	}

}