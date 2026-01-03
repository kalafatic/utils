package eu.kalafatic.protocols.annotations;

import java.io.FileNotFoundException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class AnnotationExample {

	@Documented
	@Target(ElementType.METHOD)
	@Inherited
	@Retention(RetentionPolicy.SOURCE)
	@interface MethodInfo {
		String author() default "Pankaj";

		String date();

		int revision() default 1;

		String comments();
	}

	public static void main(String[] args) throws FileNotFoundException {
		genericsTest();
		// AnnotationExample.class.
		System.out.println(new AnnotationExample().toString());;
	}

	@Override
	@MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 1)
	public String toString() {
		return "Overriden toString method";
	}

	@Deprecated
	@MethodInfo(comments = "deprecated method", date = "Nov 17 2012")
	public static void oldMethod() {
		System.out.println("old method, don't use it.");
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 10)
	public static void genericsTest() throws FileNotFoundException {
		List l = new ArrayList();
		l.add("abc");
		oldMethod();
	}

}
