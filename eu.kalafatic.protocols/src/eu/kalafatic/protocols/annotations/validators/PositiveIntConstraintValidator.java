package eu.kalafatic.protocols.annotations.validators;

import java.math.BigInteger;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import eu.kalafatic.protocols.annotations.PositiveInt;

public class PositiveIntConstraintValidator implements ConstraintValidator<PositiveInt, Number> {
	private int maxDigits;

	@Override
	public void initialize(PositiveInt constraintAnnotation) {
		maxDigits = constraintAnnotation.integer();

		if (maxDigits < 1) {
			throw new IllegalArgumentException("Invalid max size.  Max size must be a positive integer greater than 1");
		}
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		} else if (value instanceof Long || value instanceof Integer || value instanceof Short || value instanceof BigInteger) {
			String regex = "\\d{\"0,\" + maxDigits + \"}";
			return Pattern.matches(regex, value.toString());
		}
		return false;
	}
}