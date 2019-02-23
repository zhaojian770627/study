package com.zj.study.java2s.reflection.exception;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple utility class for working with the reflection API and handling
 * reflection exceptions.
 *
 * <p>
 * Only intended for internal use.
 *
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Rod Johnson
 * @author Costin Leau
 * @since 1.2.2
 */
public abstract class ReflectionUtils {
	/**
	 * Determine whether the given method explicitly declares the given
	 * exception or one of its superclasses, which means that an exception of
	 * that type can be propagated as-is within a reflective invocation.
	 * 
	 * @param method
	 *            the declaring method
	 * @param exceptionType
	 *            the exception to throw
	 * @return <code>true</code> if the exception can be thrown as-is;
	 *         <code>false</code> if it needs to be wrapped
	 */
	public static boolean declaresException(Method method, Class exceptionType) {

		Class[] declaredExceptions = method.getExceptionTypes();
		for (int i = 0; i < declaredExceptions.length; i++) {
			Class declaredException = declaredExceptions[i];
			if (declaredException.isAssignableFrom(exceptionType)) {
				return true;
			}
		}
		return false;
	}
}
