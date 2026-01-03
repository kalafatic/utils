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

import com.google.gson.Gson;

import eu.kalafatic.protocols.interfaces.ADecoder;

/**
 * The Class class DecoderJSON.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class DecoderJSON extends ADecoder {

	@SuppressWarnings("unchecked")
	@Override
	public Object decode(Object... parameters) {
		assert (parameters.length >= 2);
		assert (parameters[0] instanceof String);
		assert (parameters[1] instanceof Class);

		return new Gson().fromJson((String) parameters[0], (Class<Object>) parameters[1]);
	}

	public static void main(String[] args) {
		DecoderJSON x = new DecoderJSON();
		EncoderJSON y = new EncoderJSON(0);

		// Gson gson = new Gson();
		Car audi = x.new Car("Audi", "A4", 1.8, false);
		Car skoda = x.new Car("Škoda", "Octavia", 2.0, true);
		Car[] cars = { audi, skoda };
		Person johnDoe = x.new Person("John", "Doe", 245987453, 35, cars);
		System.err.println(y.encode(johnDoe).toString());

		// gson = new Gson();
		String json = "{\"name\":\"John\",\"surname\":\"Doe\",\"cars\":[{\"manufacturer\":\"Audi\",\"model\":\"A4\",\"capacity\":1.8,\"accident\":false},{\"manufacturer\":\"Škoda\",\"model\":\"Octavia\",\"capacity\":2.0,\"accident\":true}],\"phone\":245987453}";
		// johnDoe = gson.fromJson(json, Person.class);
		System.err.println(x.decode(json, Person.class).toString());
	}

	class Car {
		private String manufacturer;
		private String model;
		private Double capacity;
		private boolean accident;

		private Car() {}

		public Car(String manufacturer, String model, Double capacity, boolean accident) {
			this.manufacturer = manufacturer;
			this.model = model;
			this.capacity = capacity;
			this.accident = accident;
		}

		@Override
		public String toString() {
			return ("Manufacturer: " + manufacturer + ", " + "Model: " + model + ", " + "Capacity: " + capacity + ", " + "Accident: " + accident);
		}
	}

	class Person {
		private String name;
		private String surname;
		private Car[] cars;
		private int phone;
		private transient int age;

		private Person() {}

		public Person(String name, String surname, int phone, int age, Car[] cars) {
			this.name = name;
			this.surname = surname;
			this.cars = cars;
			this.phone = phone;
			this.age = age;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();

			sb.append("Name: " + name + " " + surname + "\n");
			sb.append("Phone: " + phone + "\n");
			sb.append("Age: " + age + "\n");

			int i = 0;
			for (Car item : cars) {
				i++;
				sb.append("Car " + i + ": " + item + "\n");
			}

			return sb.toString();
		}
	}
}
