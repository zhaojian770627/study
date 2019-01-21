package com.zj.study.java2s.reflection.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
	String str();

	int val();
}

public class Meta {
	@MyAnno(str = "Annotation Example", val = 100)
	public static void myMeth() {
		Meta ob = new Meta();

		try {
			Class c = ob.getClass();

			Method m = c.getMethod("myMeth");

			MyAnno anno = m.getAnnotation(MyAnno.class);

			System.out.println(anno.str() + " " + anno.val());

			System.out.println(ReflectionHelper.getAnnotationParameter(anno, "str", String.class));
		} catch (NoSuchMethodException exc) {
			System.out.println("Method Not Found.");
		}
	}

	@MyAnno(str = "Two Parameters", val = 19)
	public static void myMeth(String str, int i) {
		Meta ob = new Meta();

		try {
			Class c = ob.getClass();

			Method m = c.getMethod("myMeth", String.class, int.class);

			MyAnno anno = m.getAnnotation(MyAnno.class);

			System.out.println(anno.str() + " " + anno.val());
		} catch (NoSuchMethodException exc) {
			System.out.println("Method Not Found.");
		}
	}

	public static void main(String[] args) {
		myMeth();
		myMeth("test", 10);
	}

}
