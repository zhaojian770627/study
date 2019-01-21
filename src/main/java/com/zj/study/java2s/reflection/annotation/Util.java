package com.zj.study.java2s.reflection.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Util {

	public static Field[] findAnnotatedFields(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		Field[] declaredFields = clazz.getDeclaredFields();
		List<Field> annotatedFields = new ArrayList<Field>(declaredFields.length);
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(annotationClass)) {
				annotatedFields.add(field);
			}
		}
		return annotatedFields.toArray(new Field[annotatedFields.size()]);
	}

	public static Annotation[] findFieldAnnotations(Class<?> clazz, String fieldName) throws NoSuchFieldException {
		Field field = clazz.getDeclaredField(fieldName);
		return field.getAnnotations();
	}

	public static <T extends Annotation> T findFieldAnnotation(Class<?> clazz, String fieldName,
			Class<T> annotationClass) throws NoSuchFieldException {
		Field field = clazz.getDeclaredField(fieldName);
		return field.getAnnotation(annotationClass);
	}

	public static Method findAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		for (Method method : clazz.getMethods())
			if (method.isAnnotationPresent(annotationClass))
				return (method);
		return (null);
	}

	public static List<Method> findAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		Method[] methods = clazz.getMethods();
		List<Method> annotatedMethods = new ArrayList<Method>(methods.length);
		for (Method method : methods) {
			if (method.isAnnotationPresent(annotationClass)) {
				annotatedMethods.add(method);
			}
		}
		return annotatedMethods;
	}
}