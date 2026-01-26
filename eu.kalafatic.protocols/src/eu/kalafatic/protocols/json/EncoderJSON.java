/*******************************************************************************
 * Copyright (c) 2010, Petr Kalafatic (gemini@kalafatic.eu).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL Version 3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.txt
 *
 * Contributors:
 *     Petr Kalafatic - initial API and implementation
 ******************************************************************************/
package eu.kalafatic.protocols.json;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;

import com.google.gson.Gson;

import eu.kalafatic.protocols.annotations.PositiveInt;
import eu.kalafatic.protocols.annotations.Requires;
import eu.kalafatic.protocols.annotations.validators.PositiveIntConstraintValidator;
import eu.kalafatic.protocols.interfaces.AEncoder;

/**
 * The Class class EncoderJSON.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class EncoderJSON extends AEncoder {

	class Severity {
		public class Info implements Payload {};

		public class Error implements Payload {};
	}

	private static Validator validator;

	//@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// @Size(message = "the city is mandatory", min = 1, max = 2)
	@PositiveInt(integer = 1)
	int x;

	public EncoderJSON(@PositiveInt int x) {
		this.x = x;

		setX(x);
	}

	public static void main(String[] args) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		EncoderJSON encoderJSON = new EncoderJSON(-55);
		Set<ConstraintViolation<EncoderJSON>> violations = validator.validate(encoderJSON);

		assert violations.size() == 2;

		violations = validator.validate(encoderJSON, PositiveIntConstraintValidator.class);

		assert violations.size() == 1;

		// PositiveIntConstraintValidator v =new PositiveIntConstraintValidator();
		//
		//
		// System.err.println(v.isValid(encoderJSON.getX(), EncoderJSON.class));

	}

	@Requires(parameters = { "java.lang.Class" }, exceptions = "java.lang.NullPointerException", returns = "java.lang.String")
	@Override
	public Object encode(Object... parameters) {
		assert (parameters.length >= 1);
		assert (parameters[0] instanceof Class);

		return new Gson().toJson(parameters[0]);
	}

	@Requires(parameters = { "java.lang.Class" }, exceptions = "java.lang.NullPointerException", returns = "java.lang.String")
	void m() {

	}

	@Size(min = 1, max = 2)
	public int getX() {
		return x;
	}

	// @NotNull(message = "the city is mandatory", payload = Severity.Error.class)
	@PositiveInt(integer = 1)
	public void setX(int x) {
		this.x = x;
	}
}
