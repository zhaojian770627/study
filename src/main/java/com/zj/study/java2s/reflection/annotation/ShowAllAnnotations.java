package com.zj.study.java2s.reflection.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface What {
	String description();
}

@What(description = "An annotation test class")
@MyAnno(str = "ShowAllAnnotations", val = 99)
public class ShowAllAnnotations {
	@What(description = "An annotation test method")
	@MyAnno(str = "Testing", val = 100)
	public static void myMeth() {
		ShowAllAnnotations ob = new ShowAllAnnotations();

		try {
			Annotation annos[] = ob.getClass().getAnnotations();

			System.out.println("All annotations for ShowAllAnnotations:");
			for (Annotation a : annos)
				System.out.println(a);

			Method m = ob.getClass().getMethod("myMeth");
			annos = m.getAnnotations();

			System.out.println("All annotations for myMeth:");
			for (Annotation a : annos)
				System.out.println(a);

		} catch (NoSuchMethodException exc) {
			System.out.println("Method Not Found.");
		}
	}

	public static void main(String[] args) {
		myMeth();
	}
}
